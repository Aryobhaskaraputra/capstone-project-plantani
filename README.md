# Machine Learning's Work Overview - Plant Diseases Detection Model

This project is worked on Google Colab due to the limited system requirements of our Laptop and building
models using Tensorflow as our library and framework to classify plant diseases, especially for plants
that has crops. 
This project is worked on **Google Colab** due to the limited system requirements of our Laptop and building
models using Tensorflow as our library and framework to classify plant diseases (we mainly focused on plants that is beneficial for farmers, such as rice, tea, potato, etc). 

You can click this [**link**](https://colab.research.google.com/drive/1RKV3bpWkPrvDvpu9Sk4E51F5OABTo7Gg?usp=sharing)
to access our `Google Colab Project` for the model.
## 1. Collect and Load Datasets
We collect the data from various sources, such as from Google Images, Kaggle, and Mendeley Data, then compile and upload it to Kaggle.
We load the dataset from [**here**](https://kaggle.com/datasets/02d74928204729b3b367bc993dd155281a6e36208d0333afba42c03db0ec0d4d), this link will connect you to **Kaggle Datasets**, named `nurisyazoya/plantani`.
## 2. Pre-processing Datasets
+ The used dataset were split into 3 sets by 80% of the data in the training set, 10% in the validation set, and 10% in the test set.
+ Convert it to numpy array and resize the datasets into 224x224
## 3. Training
+ Using transfer learning MobileNetV3Large to make model because it has a better accuracy than all of the transfer learning that we have tried
+ Using Adam() as optimizer
+ Using `CategoricalCrossentropy` as a loss
+ Training with 10 epochs
+ Set the callbacks `ModelCheckpoint` to save the best model and weight
+ Set the callbacks `EarlyStopping` to stop training when accuracy doesn't improve or keeps decreasing
+ From the result, we got:
  + `accuracy: 97%`
  + `loss: 9%`
  + `val_accuracy: 93%`
  + `val_loss: 19%`
## 4. Evaluate the Model
+ We test the model for evaluation, the result is:
  + `train_loss: 21%`
  + `test_accuracy: 93%`
 
 ## 5. Saved the Model to Google Drive
 + We save the model with `.pb` and `.h5` format to Google Drive. We also convert it to `.tflite` and `json` format for another file format option. But for the finality, we used the `.h5` format to deploy the model by **Cloud Computing Team**
 + Here is the [**link**](https://drive.google.com/drive/folders/10S8oKbsQk9zrdqjeyPNZMyB8vgDKy0g5?usp=sharing) to Google Drive for our model
