package WEB;

import io.qameta.allure.Attachment;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MyPostPageTest extends AbstractTest{

    @Test
    @DisplayName("Страница моих постов с публикациями")
    void postTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        assertTrue(myPostPage.getPost1().isDisplayed());
        assertTrue(myPostPage.getPost2().isDisplayed());
        assertTrue(myPostPage.getPost3().isDisplayed());
        assertTrue(myPostPage.getPost4().isDisplayed());
    }
    @Test
    @DisplayName("Наличие изображений у постов")
    public void postsImgTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        assertTrue(myPostPage.getPost1_img().isDisplayed());
        assertTrue(myPostPage.getPost2_img().isDisplayed());
        assertTrue(myPostPage.getPost3_img().isDisplayed());
        assertTrue(myPostPage.getPost4_img().isDisplayed());
    }
          @Test
    @DisplayName("Наличие названий у постов")
    public void postsTitleTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        assertTrue(myPostPage.getPost1_title().isDisplayed());
        assertTrue(myPostPage.getPost2_title().isDisplayed());
        assertTrue(myPostPage.getPost3_title().isDisplayed());
        assertTrue(myPostPage.getPost4_title().isDisplayed());
    }
    @Test
    @DisplayName("Наличие описаний у постов")
    public void postsDescriptionTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(3000);
        assertTrue(myPostPage.getPost1_description().isDisplayed());
        assertTrue(myPostPage.getPost2_description().isDisplayed());
        assertTrue(myPostPage.getPost3_description().isDisplayed());
        assertTrue(myPostPage.getPost4_description().isDisplayed());
    }
    @Test
    @DisplayName("Проверка обреза изображений до соотношения сторон 2:3")
    public void postAspectRatioTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        getDriver().get("https://test-stand.gb.ru/?page=2");
        Thread.sleep(3000);
        assertEquals("0.6666666667", myPostPage.getImg1AspectRatio());
        assertEquals("0.6666666667", myPostPage.getImg2AspectRatio());
        assertEquals("0.6666666667", myPostPage.getImg3AspectRatio());
        assertEquals("0.6666666667", myPostPage.getImg4AspectRatio());
    }

    @Test
    @DisplayName("Заглушка при отсутствии изображения")
    void imgPlaceholderTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        assertTrue(myPostPage.getPost1_img().isDisplayed());
        assertEquals("https://test-stand.gb.ru/placeholder/800x600.gif", myPostPage.getPost1_img_src());
    }


    @Test
    @DisplayName("Переход на следующую страницу")
    public void nextPageTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(2000);
        myPostPage.nextPage();
        Thread.sleep(3000);
        assertEquals("https://test-stand.gb.ru/?page=2", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на предыдущую страницу")
    public void previousPageTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(2000);
        getDriver().get("https://test-stand.gb.ru/?page=2");
        Thread.sleep(4000);
        myPostPage.previousPage();
        Thread.sleep(4000);
        assertEquals("https://test-stand.gb.ru/?page=1", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на следующую с последней страницы")
    public void lastPageTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(2000);
        getDriver().get("https://test-stand.gb.ru/?page=3");
        Thread.sleep(4000);
        myPostPage.nextPage();
        Thread.sleep(3000);
        assertEquals("https://test-stand.gb.ru/?page=3", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на предыдущую с первой страницы")
    public void firstPageTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(3000);
        myPostPage.previousPage();
        Thread.sleep(4000);
        assertEquals("https://test-stand.gb.ru/", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Отображение сортировки по умолчанию")
    void orderDefaultTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(3000);
        getDriver().get("https://test-stand.gb.ru/?sort=createdAt&order=ASC");
        Thread.sleep(5000);
        String firstImgSrc = myPostPage.getPost1_img_src();
        myPostPage.clickHome();
        Thread.sleep(4000);
        assertEquals(firstImgSrc, myPostPage.getPost1_img_src());
    }

    @Test
    @DisplayName("Сортировка в порядке убывания")
    void orderTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(2000);
        myPostPage.change_order();
        Thread.sleep(4000);
        assertEquals("https://test-stand.gb.ru/?sort=createdAt&order=DESC", getDriver().getCurrentUrl());
        myPostPage.change_order();
        Thread.sleep(4000);
        assertEquals("https://test-stand.gb.ru/?sort=createdAt&order=ASC", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на ленту чужих постов")
    public void NotMyPostsTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(2000);
        myPostPage.switchToNotMyPosts();
        Thread.sleep(4000);
        assertEquals("https://test-stand.gb.ru/?owner=notMe&sort=createdAt&order=ASC", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на главную страницу с помощью кнопки Home")
    public void homeTest() throws InterruptedException, IOException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(2000);
        getDriver().get("https://test-stand.gb.ru/?owner=notMe&sort=createdAt&order=ASC");
        Thread.sleep(4000);
        myPostPage.clickHome();
        Thread.sleep(3000);
        assertEquals("Blog", myPostPage.getTitle_Blog());
    }

    @Test
    @DisplayName("Сообщение об отсутствии постов в блоге без публикаций")
    void postsTest() throws InterruptedException {
        MyPostPage myPostPage = new MyPostPage(getDriver());
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginMax20();
        Thread.sleep(1000);
        assertEquals("No items for your filter", myPostPage.getMessage());
    }
}
