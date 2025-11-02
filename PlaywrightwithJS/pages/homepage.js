const { expect } = require('@playwright/test');

class HomePage {
  constructor(page) {
    this.page = page;

    // Header & navigation
    this.productsLink = page.locator('a[href="/products"]');
    this.carousel = page.locator('(//div[@class="carousel-inner"])[1]');
    //cart
    this.cartbutton = page.locator('(//i[@class="fa fa-shopping-cart"])[1]');

    // Footer & subscription
    this.footer = page.locator('//footer[@id="footer"]');
    this.subscriptionHeader = page.locator('div[class="single-widget"] h2');
    this.emailInput = page.locator('//input[@id="susbscribe_email"]');
    this.subscribeButton = page.locator('//button[@id="subscribe"]');
    this.successMsg = page.locator('//div[@class="alert-success alert"]');
  }

  /** Navigates to home page and verifies it loaded successfully */
  async gotoHome() {
    await this.page.goto('http://automationexercise.com/');
    await expect(this.carousel).toBeVisible();
  }

  /** Navigates to Products page */
  async navigateToProducts() {
    await this.productsLink.click();
    await expect(this.page).toHaveURL('https://automationexercise.com/products');
  }

  /** Scrolls to footer and verifies Subscription section */
  async verifySubscriptionSection() {
    // Scroll to the footer
    await this.page.evaluate(() => window.scrollTo(0, document.body.scrollHeight));

    // Ensure footer and subscription text are visible
    await expect(this.footer).toBeVisible();
    await expect(this.subscriptionHeader).toBeVisible();

    // Validate "SUBSCRIPTION" heading text
    await expect(this.subscriptionHeader).toHaveText(/subscription/i);
  }

  /** Enters email and verifies successful subscription message */
  async subscribeWithEmail(email) {
    await this.emailInput.fill(email);
    await this.subscribeButton.click();

    // Wait for success message
    await expect(this.successMsg).toBeVisible({ timeout: 7000 });
    await expect(this.successMsg).toContainText('You have been successfully subscribed!');
  }

  async cart(){
    await this.cartbutton.click();
  }
}

module.exports = { HomePage };
