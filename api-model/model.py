import os
import numpy as np
import matplotlib
import tensorflow as tf
from PIL import Image
import keras.utils as image
from tensorflow.keras.preprocessing.image import img_to_array, load_img
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow import keras

def load_model():
    model = keras.models.load_model("model_08-0.90.h5")
    return model

def plant_disease_prediction(file_path):
    img = image.load_img(file_path) 
    p = image.img_to_array(img)
    p = p/255.0
    p = tf.image.resize(p, (300, 300))
    p = np.expand_dims(p, axis=0)

    plant_disease_class = ['Cabai - Antraknosa', 'Cabai - Rebah Kecambah', 'Cabai - Daun Keriting', 'Cabai - Bercak Daun', 'Cabai - Sehat',
                         'Cabai - Kutu Kebul/Kutu Putih', 'Cabai - Virus Kuning', 'Jagung - Bercak Daun Abu-Abu', 'Jagung - Karat Daun',
                         'Jagung - Hawar Daun Jagung', 'Jagung - Sehat', 'Jeruk - Bercak Hitam', 'Jeruk - Naga Kuning', 'Jeruk - Kanker', 
                         'Jeruk - Melanosis (Busuk Pangkal Buah)', 'Jeruk - Kudis', 'Jeruk - Sehat', 'Kedelai - Bakteri Pustul', 'Kedelai - Embun Bulu',
                         'Kedelai - Bercak Mata Katak', 'Kedelai - Hama Kumbang Berbisa', 'Kedelai - Hama Kumbang Mentimun', 'Kedelai - Hama Ulat', 'Kedelai - Hawar Daun Bakteri',
                         'Kedelai - Karat Daun', 'Kedelai - Virus Mosaik', 'Kedelai - Embun Tepung', 'Kedelai - Sehat', 'Kedelai - Hawar Daun Selatan',
                         'Kedelai - Sindrom Kematian Mendadak', 'Kedelai - Bercak Target', 'Kedelai - Mosaik Kuning', 'Kentang - Bercak Kering', 'Kentang - Busuk Akhir',
                         'Kentang - Sehat', 'Kopi Arabika - Bercak Daun Abu-Abu', 'Kopi Arabika - Karat Daun', 'Kopi Arabika - Penggorok Daun', 
                         'Kopi Arabika - Hawar Daun Phoma', 'Kopi - Tungau Merah', 'Kopi - Sehat', 'Padi - Hawar Daun', 'Padi - Bercak Coklat',
                         'Padi - Kurang Sehat', 'Padi - Ketombe Daun', 'Padi - Sehat', 'Teh - Bercak Daun Alga (Ganggang)', 'Teh - Antraknosa', 
                         'Teh - Bercak Mata Burung', 'Teh - Bercak Coklat', 'Teh - Bintik Daun Merah', 'Teh - Sehat', 'Tomat - Bintik Bakteri',
                         'Tomat - Bercak Kering', 'Tomat - Bercak Akhir', 'Tomat - Jamur Daun Tomat / Kapang Daun Tomat', 'Tomat - Virus Mosaik', 
                         'Tomat - Sehat', 'Tomat - Bercak Daun Septoria', 'Tomat - Tungau Laba-Laba', 'Tomat - Bercak Target', 'Tomat - Virus Kuning Keriting']
    model = load_model()
    pred = model(p)
    index = np.argmax(pred)
    pred_pd = plant_disease_class[index]

    return pred_pd

def plants_disease_name(name_path):
    x = name_path.split(" - ")[1]
    if x != 'Sehat':
        return x
    else:
        return "---"

def result_predict(image_path):
    pred_pd = plant_disease_prediction(image_path)
    tanaman = pred_pd.split(" - ")[0]
    penyakit = plants_disease_name(pred_pd)

    return {"tanaman": tanaman, "penyakit": penyakit}

