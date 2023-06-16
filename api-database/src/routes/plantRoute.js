const express = require('express');
const { getAllPlants, getPlantById} = require('../controller/plantController');


const router = express.Router();

//menampilkan seluruh tanaman
router.get('/', getAllPlants );
//router.get('/', getAllPlant);

//menampilkan detail tanaman
router.get('/:plantId', getPlantById);

module.exports = router;