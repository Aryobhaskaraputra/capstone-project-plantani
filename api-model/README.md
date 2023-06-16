## API MACHINE LEARNING-PlanTani
## Cara menginstal API ini
1. Gunakan python versi 3.9 (Rekomendasi)
2. Clone repositori API ini
3. Instal semua requirment yang diperlukan (didalam requirements.txt)
4. Buat folder baru dengan nama "uploads"
```
pip install -r requirments.txt
```
3. Menjalankan API ini
```
// dalam mode debug atau developmnt 
python3 app.py

// dalam mode produksi 
gunicorn server_ml:app
```
## Cara menggunakan/consume API ini 
Cukup akses rute API yang tersedia,
- Menggunakan curl
```
// POST 
curl -X POST -F "file="@sesuaikan dengan nama file foto tanaman yang tersedia di lokal" "link.api.com/predict"
```
- Menggunakan folder test/ yang sudah tersedia
```
 // POST 
 1. Masukkan setiap gambar tanaman yang ingin di predik ke folder test/ tersebut
 2. Ubah skrip curl dibawah sesuai kebutuhan, skrip ini tersedia di folder test/
 // Contoh:
 url="link.api.com//"
curl -X POST -F "file="@sesuaikan dengan nama file foto tanaman yang tersedia di lokal" $url"/predict"
 3. Buka terminal (Recommended git bash),masuk direktori folder test (cd test), kemduian ketikkan 
    di terminal : ./test.sh


