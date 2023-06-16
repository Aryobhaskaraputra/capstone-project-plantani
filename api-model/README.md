## API MACHINE LEARNING-PlanTani
## How to install this API
1. Use python version 3.9 (Recommendation)
2. Clone this API repository
3. Install all required requirements (inside requirements.txt)
4. Create a new folder with the name "uploads"
```
pip install -r requirments.txt
```
3. Running this API
```
// in development mode 
python3 app.py

// in production mode 
gunicorn server_ml:app
```
## How to use/consume this API 
Just access available API route,
- Using curl
```
// POST 
curl -X POST -F "file="@adjust to the name of the plant photo file available locally" "link.api.com/predict"
```
- Using the existing test folder
```
 // POST 
 1. Insert each plant image that you want to predict into the test/ folder
 2. Modify the curl script below as needed, this script is available in the test folder
 // Example:
 url="link.api.com//"
curl -X POST -F "file="@sadjust to the name of the plant photo file available locally" $url"/predict"
 3. Open the terminal (Recommended git bash), enter the test folder directory (cd test), then type 
    in git bash terminal : ./test.sh
