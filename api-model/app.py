from flask import Flask, jsonify, request
import os
from model import result_predict

app = Flask(__name__)

UPLOAD_FOLDER = 'uploads'
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg'}
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

def allowed_file(filename):
    return '.' in filename and \
    filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/')
def ml_api():
    return jsonify({'code':'200','message': 'API - ml_api'})

@app.route('/predict', methods=['POST'])
def predict():
    if 'file' not in request.files:
        return 'No file found', 400

    file = request.files['file']

    if file.filename == '':
        return 'No file selected', 400

    if not allowed_file(file.filename):
        return 'Invalid file format', 400

    file_path = os.path.join(app.config['UPLOAD_FOLDER'], file.filename)
    file.save(file_path)

    result = result_predict(file_path)

    os.remove(file_path)

    return result

@app.errorhandler(404)
def handle_invalid_route(e):
    return 'Invalid API Request', 404

if __name__ == '__main__':
    app.run()
