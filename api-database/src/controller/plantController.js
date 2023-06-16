const plantModels = require("../model/plantModel");
const { Storage } = require('@google-cloud/storage');
const storage = new Storage({
  keyFilename: './src/config/plantani-app-08e56d7a228d-serviceAccountKey.json',
});

// Mendapatkan semua data tanaman beserta gambar
const getAllPlants = async (req, res) => {
  try {
    const plants = await plantModels.getAllPlant();

    // Mengambil URL gambar dari Cloud Storage
    const bucketName = 'picture-assets-plantani';

    const plantsWithImages = await Promise.all(
      plants.map(async (plant) => {
        const imageName = plant.plantName; // Nama file gambar dari properti 'plantName'
       const imagePath = `Plants/${imageName}.png`; // Menambahkan path folder "Plants/"

        const imageUrl = await storage
          .bucket(bucketName)
          .file(imagePath)
          .getSignedUrl({
            version: 'v4',
            action: 'read',
            expires: Date.now() + 15 * 60 * 1000,
          })
          .then(([url]) => url);

        return { ...plant, imageUrl };
      })
    );

    res.status(200).json({
      message: 'Berhasil mendapatkan semua tanamanan',
      plants: plantsWithImages,
    });
  } catch (error) {
    res.status(500).json({
      message: 'Server error',
      error: error.message,
    });
  }
};


const getPlantById = async (req, res) => {
  const { plantId } = req.params;
  try {
    const plant = await plantModels.getPlantById(plantId);

    // Mengambil URL gambar dari Cloud Storage
    const bucketName = 'picture-assets-plantani';

    if (plant) {
      const imageName = plant.plantName; // Nama file gambar dari properti 'plantName'
      const imagePath = `Plants/${imageName}.png`; // Menambahkan path folder "Plants/"

      const imageUrl = await storage
        .bucket(bucketName)
        .file(imagePath)
        .getSignedUrl({
          version: 'v4',
          action: 'read',
          expires: Date.now() + 15 * 60 * 1000,
        })
        .then(([url]) => url);

      const plantWithImage = { ...plant, imageUrl };

      res.status(200).json({
        message: 'Berhasil mendapatkan tanaman',
        plant: plantWithImage,
      });
    } else {
      res.status(404).json({
        message: "Tanaman tidak ditemukan",
      });
    }
  } catch (error) {
    res.status(500).json({
      message: "Server Error",
      serverMessage: error,
    });
  }
};

module.exports = {
  getAllPlants,
  getPlantById,

};
