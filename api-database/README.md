# Api Documentation

## Auth Endpoint

- base url : `https://<projectName>.et.r.appspot.com/`
- base image url : `https://storage.googleapis.com/<bucket_name>/`

### User Registration

- Path : `/register`
- Method : `POST`
- Response :

```json
{
    "message": "Berhasil membuat akun baru",
    "data": {
        "userId": 0,
        "name": "user3",
        "username": "user3",
        "email": "user3@gmail.com",
        "phoneNumber": "1234567891012",
        "locationId": "7"
    }
}
```

### User Login

- Path : `/login`
- Method : `GET`
- Response :

```json
{
    "message": "Berhasil Login",
    "data": {
        "userId": "fd2cb428-19d0-4469-ba81-d8cd2dd4b237",
        "username": "user3",
        "name": "user3"
    }
}
```

### Profile

- Path : `/users/:userId`
- Method : `GET`
- Response :

```json
{
    "name": "user3",
    "username": "user3",
    "email": "user3@gmail.com",
    "password": "$2b$10$Ykl8CdAAWj/zdUFJ1d41/.wajv2FBSAcVCcnzPX1E/UecRNqFBkBK",
    "phoneNumber": "1234567891012",
    "locationName": "Kepulauan Riau"
}
```

### Update Profile

- Path : `/users/update-profile/:userId`
- Method : `PATCH`
- Response :

```json
{
    "message": "UPDATE user success",
    "data": {
        "name": "testuser3",
        "username": "testuser3",
        "email": "testuser3@gmail.com",
        "password": "$2b$10$Ykl8CdAAWj/zdUFJ1d41/.wajv2FBSAcVCcnzPX1E/UecRNqFBkBK",
        "phoneNumber": "12345678889",
        "locationName": "Sulawesi Selatan"
    }
}
```
### Change Password

- Path : `/users/:userId/changepassword`
- Method : `PUT`
- Response :

```json
{
    "message": "Password berhasil diperbaharui"
}
```

## App Endpoint

- base url : `https://<projectName>.et.r.appspot.com/`
- base image url : `https://storage.googleapis.com/<bucket_name>/`

### Add myPlants

- Path : `/users/:userId/add-my-plants`
- Method : `POST`
- Response :

```json
{
    "message": "Add plant to my plants success",
    "data": {
        "myPlantId": 0,
        "user": {
            "name": "testuser3",
            "username": "testuser3",
            "email": "testuser3@gmail.com",
            "password": "$2b$10$Ykl8CdAAWj/zdUFJ1d41/.wajv2FBSAcVCcnzPX1E/UecRNqFBkBK",
            "phoneNumber": "12345678889",
            "locationName": "Sulawesi Selatan"
        },
        "plant": {
            "plant_id": 2,
            "plantName": "Jeruk",
            "latinPlantName": "Citrus\n",
            "description": "lorem dolor sit amet, consectetur adipiscing..",
            "temperature": "Cara merawat buah jeruk pasca panen adalah dengan menyortir jeruk dan ditempatkan pada lokasi dengan suhu rendah 8º – 10º C\n",
            "pH": "Tanaman jeruk memerlukan irigasi dan drainase dengan nilai keasaman (pH) 6 - 7\n",
            "treatment": "lorem dolor sit amet, consectetur adipiscing..",
            "category_id": 1
        }
    }
}
```
### Get All myPlants

- Path : `/users/:userId/myplants`
- Method : `GET`
- Response :

```json
{
    "message": "Menampilkan MyPlants success",
    "userId": "fd2cb428-19d0-4469-ba81-d8cd2dd4b237",
    "data": [
        {
            "plantName": "Jeruk",
            "latinPlantName": "Citrus\n",
            "category_id": 1
        }
    ]
}
```
### Get All Plats

- Path : `/plants`
- Method : `GET`
- Response :

```json
{
    "message": "Berhasil mendapatkan semua tanamanan",
    "plants": [
        {
            "plant_id": 1,
            "plantName": "Cabai",
            "latinPlantName": " Capsium",
            "description": "lorem dolor sit amet, consectetur adipiscing..",
            "climate": "Waktu tanam yang baik untuk lahan kering adalah pada akhir musim hujan (Maret - April). Untuk memperoleh harga cabai yang tinggi, bisa juga dilakukan pada bulan Oktober dan panen pada bulan Desember, walaupun kemungkinan memiliki risiko kegagalan. ",
            "temperature": "Kondisi ideal tumbuh kembang tanaman Cabai memiliki syarat suhu udara 180C-300C dan kelembaban tanah 60%-80%.",
            "pH": "pH tanah yang ideal sekitar 5 - 6.",
            "treatment": "lorem dolor sit amet, consectetur adipiscing..",
            "category_id": 1,
            "imageUrl": "https://storage.googleapis.com/picture-assets-plantani/Plants/Cabai.png"
        },
    ]
}
```
### Get Plants by ID

- Path : `/plants/:plantID`
- Method : `GET`
- Response :

```json
{
    "message": "Berhasil mendapatkan semua tanamanan",
    "plants": [
        {
            "plant_id": 1,
            "plantName": "Cabai",
            "latinPlantName": " Capsium",
            "description": "lorem dolor sit amet, consectetur adipiscing..",
            "climate": "Waktu tanam yang baik untuk lahan kering adalah pada akhir musim hujan (Maret - April). Untuk memperoleh harga cabai yang tinggi, bisa juga dilakukan pada bulan Oktober dan panen pada bulan Desember, walaupun kemungkinan memiliki risiko kegagalan. ",
            "temperature": "Kondisi ideal tumbuh kembang tanaman Cabai memiliki syarat suhu udara 180C-300C dan kelembaban tanah 60%-80%.",
            "pH": "pH tanah yang ideal sekitar 5 - 6.",
            "treatment": "lorem dolor sit amet, consectetur adipiscing..",
            "category_id": 1,
            "imageUrl": "https://storage.googleapis.com/picture-assets-plantani/Plants/Cabai.png"
        },
    ]
}{
    "message": "Berhasil mendapatkan tanaman",
    "plant": {
        "plant_id": 8,
        "plantName": "Teh",
        "latinPlantName": "Camellia sinensis",
        "description": "lorem dolor sit amet, consectetur adipiscing..",
        "climate": "lorem dolor sit amet, consectetur adipiscing..",
        "temperature": "lorem dolor sit amet, consectetur adipiscing..",
        "pH": "Unsur hara akan tersedia secara optimal untuk tanaman teh pada pH: 4,5 - 5,5.",
        "treatment": "lorem dolor sit amet, consectetur adipiscing..",
        "category_id": 1,
        "imageUrl": "https://storage.googleapis.com/picture-assets-plantani/Plants/Teh.png?X-Goog-Algorithm=GOOG4-RSA-SHA256&X-Goog-Credential=bucket-viewer%40plantani-app.iam.gserviceaccount.com%2F20230616%2Fauto%2Fstorage%2Fgoog4_request&X-Goog-Date=20230616T063716Z&X-Goog-Expires=900&X-Goog-SignedHeaders=host&X-Goog-Signature=4a53218d05ad50dc3aa1418555788929d7ea211fb6586e8759d6c6958e7495d76a881df4b20193b307abb3d622468e133df49eda5b0d47d3ecc9166b5bd287a456cdbe9b3264d807659d372e06b390bbbfe5239e600a93658455cdd9aa110a9ad4482650d9cfe1dba2954f1766acf64096ef4b86252fe7fd0a7e56d47076367eb6674ab4de9bc2fc7cfc48dd02fa06da0157951950f36b74dc9eb3a0a214f53e9cfc24569507b30f4a6adf54a7d8a3909bd1e11fa4aa06e0097574cfb445711757911b59b1d29ef4588a144642fbb0a23336a35552b876899305438fade972d1501f2956f7a0d35a8aaaa48929e3260a239a7f43263e376ec83f29c2126a0dbd"
    }
}
```

### Get All Plats Diseases

- Path : `/plants-disease`
- Method : `GET`
- Response :

```json
{
    "message": "Berhasil mendapatkan semua penyakit tanaman",
    "plantDisease": [
        {
            "disease_id": 1,
            "plantDiseaseName": "Anthracnose\r",
            "indonesiaPlantDiseaseName": "Antraknosa\n",
            "description": "lorem dolor sit amet, consectetur adipiscing..",
            "treatment": "lorem dolor sit amet, consectetur adipiscing..",
            "imageUrl": "https://storage.googleapis.com/picture-assets-plantani/plantDisease/1.png?X-Goog-Algorithm=GOOG4-RSA-SHA256&X-Goog-Credential=bucket-viewer%40plantani-app.iam.gserviceaccount.com%2F20230616%2Fauto%2Fstorage%2Fgoog4_request&X-Goog-Date=20230616T064031Z&X-Goog-Expires=900&X-Goog-SignedHeaders=host&X-Goog-Signature=00dafe265eb3db3400ca947e88dfeab64d3f1d52bbed70ae0fade33b629df49a8d285262e04b1373f10739db35f110eb8dec982099c24599f8970c00447256e7d4e64b7d6759884dfae054fd04c8dd5a625dc43a4012826846b8bb5ed07d8ecfff258f85109a614351d40bf3e4188050dac9237aa2d33dc89c43ed6e4c25d290f715d40efa39c9b8e48f9c4a0c70d357699019babbf8c0fca639ffdfb1e7d0f9d14077754a6ecf40e8997e0708ade879c7bf083a82f5c4fb12d742f3cef24ad71a8cf058903635484320eb503136229f72df1e0de24b05d82b2799e33b6b0c13735977514e474c5802f64c806e435e75073af8f938cc0a5dd1689c5c95049429"
        },
        {
            "disease_id": 2,
            "plantDiseaseName": "Dumping Off\r",
            "indonesiaPlantDiseaseName": "Rebah Kecambah\n",
            "description": "lorem dolor sit amet, consectetur adipiscing..",
            "treatment": "lorem dolor sit amet, consectetur adipiscing..",
            "imageUrl": "https://storage.googleapis.com/picture-assets-plantani/plantDisease/2.png?X-Goog-Algorithm=GOOG4-RSA-SHA256&X-Goog-Credential=bucket-viewer%40plantani-app.iam.gserviceaccount.com%2F20230616%2Fauto%2Fstorage%2Fgoog4_request&X-Goog-Date=20230616T064031Z&X-Goog-Expires=900&X-Goog-SignedHeaders=host&X-Goog-Signature=79e69d381daac43740c2dc025369749ae540355cfedc41475b793e8b409ca93fbd3ca3d2dfdeb09f155dae9310f9c4be009bf2fc5f5e59777a14825ad115a465b026468579bf7f00077bb5070dea3b5df8e6cf9cd8c32734be1b22cde83e6a9d25053e00a2b3e9d04ed413fc5f8ed503c881d0f179120e85e63cec6f1b9ed2ea3f09747d0d35241f17f0072c3222c7c22dcdc9f9b7d8d2edb121f9e663a38d88ef7e0c91394889cc1811068d629c8a56b872ac7a928861cfa19c233facb94699c1438ca15ed1b6b3ae115b6e3a4eb6c49d725375b190c6e68bf843a870c4108ef23321cd8e4739c3d9edd3bc602f61fede7ab60e81eaef82c5c00ed5540d8550"
        },
    ]
}
```

### Get Plants by ID

- Path : `/plants-disease/:plantdiseaseId`
- Method : `GET`
- Response :

```json
{
    "message": "Berhasil mendapatkan penyakit tanaman berdasarkan ID",
    "plantDisease": {
        "disease_id": 40,
        "plantDiseaseName": "Septoria Leaf Spot\r",
        "indonesiaPlantDiseaseName": "Bercak Daun Septoria​​\n",
        "description": "lorem dolor sit amet, consectetur adipiscing..",
        "imageUrl": "https://storage.googleapis.com/picture-assets-plantani/plantDisease/40.png?X-Goog-Algorithm=GOOG4-RSA-SHA256&X-Goog-Credential=bucket-viewer%40plantani-app.iam.gserviceaccount.com%2F20230616%2Fauto%2Fstorage%2Fgoog4_request&X-Goog-Date=20230616T064200Z&X-Goog-Expires=900&X-Goog-SignedHeaders=host&X-Goog-Signature=1b60c6a01a92a903e90340cd4d451e058ee7f6f0465881796b4722bce44077b479ee887e939976d94fab0d2d61b30968484e09282f615ae6c28b19614f1ef22d2ca356cb2da11e96204a7df7aaed30a7b6ebce40d791bb4ece45519b47bb3bcd585dd69cfb81bc0aa6254e40d5d599bc0fee85bac64051f3cc13385478a02762d64ef7158e292d026e9a61638b2edb8555115f9ae9f908d5a9c01e1b53e9d0aa82f5e784248074f5bb550a0fa9aa43edee89718978ef1f5806416148899a7cb9ebee6aca7574c20c5782d04280c834210c61cff1fe7524ba1ac4a691fa7205d8f43a71821a6a90dd22fe60970cf9fa0e69db3605894cd235a5edd8382425eaad"
    }
}
```