const dbPool = require('../config/database');


const getAllPlant = async () => {
  const SQLQuery = 'SELECT * FROM plants';
  try {
    const [rows] = await dbPool.query(SQLQuery);
    return rows;
  } catch (error) {
    throw error;
  }
};
  
//menampilkan plant berdasarkan id
const getPlantById = async (plantId) => {
    const SQLQuery = `SELECT * FROM plants WHERE plant_id = ?`;
    const values = [plantId]
    try {
        const [rows] = await dbPool.execute(SQLQuery, values);
        return rows[0]; // Mengembalikan data tanaman jika ditemukan
      } catch (error) {
        throw error;
      }
    };

module.exports = {
    getAllPlant, 
    getPlantById
};