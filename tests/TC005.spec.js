// tests/signup-existing-email.spec.js
import { test } from '@playwright/test';
const { SignupPage } = require('../pages/signup');
const { readExcelData } = require('../pages/readexcel');

const filePath = '/Users/abhishekamat/Documents/Ecom_PWJS/testdata/testdata.xlsx';
const loginData = readExcelData(filePath, 'login'); // Sheet "login"

test.describe('Signup with existing email', () => {
  const existingUser = loginData[0]; // take the first row (valid user)

  test('Verify signup with existing email shows error', async ({ page }) => {
    const signupPage = new SignupPage(page);

    // Navigate to signup
    await signupPage.navigateToLogin();

    // Try signing up with an existing email from Excel
    await signupPage.signupwithexistingEmail(existingUser.TestCase, existingUser.Username);

    // Assert failure message
    await signupPage.assertSignupFail();
  });
});
