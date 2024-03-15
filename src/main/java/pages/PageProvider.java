package pages;

import org.openqa.selenium.WebDriver;
import pages.elements.Breadcrumbs;

public class PageProvider {

    private WebDriver webDriver;
    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public LoginPage loginPage() {
        return new LoginPage(webDriver);
    }
    public MainPage mainPage() {return new MainPage(webDriver);}
    public CartPage cartPage() {return new CartPage(webDriver);}
    public PostPage postPage() {return new PostPage(webDriver);}
    public BlogPage blogPage() {return new BlogPage(webDriver);}
    public SearchResultsPage searchPage() {
        return new SearchResultsPage(webDriver);
    }
    public ProductPage productPagePage() {return new ProductPage(webDriver);}
    public Breadcrumbs breadcrumbs() {
        return new Breadcrumbs(webDriver);
    }

}
