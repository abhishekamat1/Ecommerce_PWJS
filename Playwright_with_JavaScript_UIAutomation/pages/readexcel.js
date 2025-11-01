// utils/readExcel.js
const xlsx = require('xlsx');

function readExcelData(filePath, sheetName) {
  const workbook = xlsx.readFile(filePath);
  const sheet = workbook.Sheets[sheetName];
  if (!sheet) throw new Error(`Sheet "${sheetName}" not found in ${filePath}`);
  return xlsx.utils.sheet_to_json(sheet, { raw: false }); // ensure strings
}

module.exports = { readExcelData };
