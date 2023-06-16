const dbPool = require("../config/database");

// menampilkan semua data PlantDiseases
const getAllPlantDiseases = async () => {
  const SQLQuery = "SELECT * FROM plantdiseases";
  try {
    const [rows] = await dbPool.query(SQLQuery);
    return rows;
  } catch (error) {
    throw error;
  }
};



// menampilkan detail plant disease
const getPlantDiseasesById = async (plantDiseaseId) => {
  const SQLQuery = `SELECT * FROM plantdiseases WHERE disease_id=?`;
  const values = [plantDiseaseId];

  try {
    const [rows] = await dbPool.execute(SQLQuery, values);
    return rows[0];
  } catch (error) {
    throw error;
  }
};

module.exports = {
  getAllPlantDiseases,
  getPlantDiseasesById,
};
