const pdModel = require("../model/plantDiseaseModel");
const { Storage } = require('@google-cloud/storage');
const storage = new Storage({
  keyFilename: './src/config/plantani-app-08e56d7a228d-serviceAccountKey.json',
});

//menampilkan semua plant disease
const getAllPlantDiseases = async (req, res) => {
  try {
    const plantDiseases = await pdModel.getAllPlantDiseases();

    // Mengambil URL gambar dari Cloud Storage
    const bucketName = 'picture-assets-plantani';

    const plantDiseasesWithImages = await Promise.all(
      plantDiseases.map(async (plantDisease) => {
        const imageName = plantDisease.disease_id; // Nama file gambar dari properti 'disease_id'
        const imagePath = `plantDisease/${imageName}.png`; // Menambahkan path folder "plantDisease/"

        const imageUrl = await storage
          .bucket(bucketName)
          .file(imagePath)
          .getSignedUrl({
            version: 'v4',
            action: 'read',
            expires: Date.now() + 15 * 60 * 1000,
          })
          .then(([url]) => url);

        return {...plantDisease,imageUrl};
      })
    );

    res.status(200).json({
      message: 'Berhasil mendapatkan semua penyakit tanaman',
      plantDisease: plantDiseasesWithImages,
    });
  } catch (error) {
    res.status(500).json({
      message: 'Server error',
      serverMessage: error,
    });
  }
};


//menampilkan detail plant disease
const getPlantDiseasesById = async (req, res) => {
  const { plantDiseaseId } = req.params;
  try {
    const plantDisease = await pdModel.getPlantDiseasesById(plantDiseaseId);

    // Mengambil URL gambar dari Cloud Storage
    const bucketName = 'picture-assets-plantani';

    if (plantDisease) {
      const imageName = plantDisease.disease_id; // Nama file gambar dari properti 'disease_id'
      const imagePath = `plantDisease/${imageName}.png`; // Menambahkan path folder "plantDisease/"

      const imageUrl = await storage
      .bucket(bucketName)
      .file(imagePath)
      .getSignedUrl({
        version: 'v4',
        action:'read',
        expires: Date.now() + 15 * 60 * 1000,
      })
      .then(([url]) => url)

      const plantDiseasesWithImages = {...plantDisease, imageUrl}
      res.status(200).json({
          message: 'Berhasil mendapatkan penyakit tanaman berdasarkan ID',
          plantDisease: plantDiseasesWithImages,
        }
      );
    }else{
      res.status(404).json({
              message: ' ID Plant disease tidak ditemukan',
            });
    }
  } catch (error) {
    res.status(500).json({
      message: "Server error",
      serverMessage: error,
    });
  }
};

module.exports = {
  getAllPlantDiseases,
  getPlantDiseasesById,
};
