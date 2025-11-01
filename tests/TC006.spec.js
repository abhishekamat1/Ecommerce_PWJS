// tests/contactus.spec.js
import { test } from '@playwright/test';
const { ContactUsPage } = require('../pages/contactuspage');
const { readExcelData } = require('../pages/readexcel');

// Excel file location
const filePath = '/Users/abhishekamat/Documents/Ecomm_PWJS/testdata/testdata.xlsx';
const contactUsData = readExcelData(filePath, 'contactus');

test.describe('Contact Us form tests', () => {
  for (const row of contactUsData) {
    test(`Submit Contact Us form for ${row.Name}`, async ({ page }) => {
      const contactUsPage = new ContactUsPage(page);

      await contactUsPage.gotoHome();
      await contactUsPage.openContactForm();

      await contactUsPage.fillContactForm(row);
      await contactUsPage.submitForm();
      // await page.pause();
      // await contactUsPage.assertSuccess();
    });
  }
});
