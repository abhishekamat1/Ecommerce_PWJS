// utils/writeExcel.js
const xlsx = require('xlsx');
const fs = require('fs');

function writeExcelData(filePath, sheetName, data) {
  let workbook;
  if (fs.existsSync(filePath)) {
    // If file already exists → load it
    workbook = xlsx.readFile(filePath);
  } else {
    // Else → create a new workbook
    workbook = xlsx.utils.book_new();
  }

  let worksheet = workbook.Sheets[sheetName];
  let existingData = [];

  if (worksheet) {
    existingData = xlsx.utils.sheet_to_json(worksheet);
  }

  // Append new row
  existingData.push(data);

  // Convert JSON back to worksheet
  const newWorksheet = xlsx.utils.json_to_sheet(existingData);
  workbook.Sheets[sheetName] = newWorksheet;

  // Save workbook
  xlsx.writeFile(workbook, filePath);

  console.log(`Data written to ${filePath} (${sheetName})`);
}

module.exports = { writeExcelData };
