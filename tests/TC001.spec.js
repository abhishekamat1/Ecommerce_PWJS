//Testing signup
import { test } from '@playwright/test';
const { SignupPage } = require('../pages/signup');
const { readExcelData } = require('../pages/readexcel');
const { writeExcelData } = require('../pages/writeexcel');

const filePath = '/Users/abhishekamat/Documents/Ecom_PWJS/testdata/testdata.xlsx';

// Read input data
const signupData = readExcelData(filePath, 'Signup');

test.describe('Signup Tests (Excel Data)', () => {

  for (const row of signupData) {
    test(`Signup for ${row.BaseName} (${row.BaseEmail})`, async ({ page }) => 
      {
      const signupPage = new SignupPage(page);

      // Navigate
      await signupPage.navigateToLogin();

      // Ensure unique email
      const { currentName, currentEmail } = await signupPage.signupWithUniqueEmail(
        row.BaseName,
        row.BaseEmail
      );

      // Log to console
      console.log(`Signup Credentials => Name: ${currentName}, Email: ${currentEmail}, Password: ${row.Password}`);

      // Fill details from Excel
      await signupPage.fillRegistrationForm(row);

      // Assert success
      await signupPage.assertSignupSuccess();

      //Write credentials back into Excel (new sheet "SignedUpUsers")
      writeExcelData(filePath, 'SignedUpUsers', {
        TestCase: row.TestCase,
        Name: currentName,
        Email: currentEmail,
        Password: row.Password,
        Status: 'SignedUp'
      });
    });
  }

});
