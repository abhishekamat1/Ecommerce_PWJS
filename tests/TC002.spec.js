// tests/login.test.js
import { test } from '@playwright/test';
const { LoginPage } = require('../pages/loginpage');
const { readExcelData } = require('../pages/readexcel');

const filePath = '/Users/abhishekamat/Documents/Ecom_PWJS/testdata/testdata.xlsx';

// Read login data from "Login" sheet
const loginData = readExcelData(filePath, 'login');

test.describe('Login Tests', () => {

  for (const row of loginData) {
    test(`Login test: ${row.TestCase}`, async ({ page }) => {
      const loginPage = new LoginPage(page);

      // Navigate
      await loginPage.gotoHome();
      await loginPage.navigateToLogin();

      // Attempt login
      await loginPage.login(row.Username, row.Password);

      // Assert based on ExpectedResult
      if (row.ExpectedResult === "Success") {
        await loginPage.assertLoginSuccess(row.Username.split('@')[0]); 
        // 👆 If you want "Hyperion", adjust Excel column instead of parsing
      } else {
        await loginPage.assertLoginFail();
      }
    });
  }

});
