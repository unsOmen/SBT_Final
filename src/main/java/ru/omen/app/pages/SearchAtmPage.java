package ru.omen.app.pages;

import lib.Init;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.AddParameterEvent;

import java.text.ParseException;
import java.util.List;

/**
 * Created by OmeN on 23.05.2016.
 */
public class SearchAtmPage extends AnyPages {

    @FindBy(xpath = "//input[contains(@value, 'filial')]")
    private WebElement checkboxFilial;
    private String labelFilial = "//label[contains(., 'Отделения')]";

    @FindBy(xpath = "//input[contains(@value, 'itt')]")
    private WebElement checkboxTerminal;
    private String lableTerminal = "//label[contains(., 'Платёжные устройства')]";

    @FindBy(xpath = "//ul[contains(@id, 'branchListItems')]//span[contains(@class, 'item-list-icon')]")
    private List<WebElement> listItemResult;

    @FindBy(xpath = "//ul[contains(@id, 'branchListItems')]//li//p[contains(.,'На расстоянии')]")
    private List<WebElement> listDistanceResult;

    private String xpathTextHeader = "//h1[contains(., 'Отделения и банкоматы')]";
    private String xpathBtnShowMore = "//button[contains(@class, 'show-more')]";

    private static final String CLASS_FILIAL_NAME = "filial";
    private static final String CLASS_TERMINAL_NAME = "itt";

    public SearchAtmPage() {
        new WebDriverWait(Init.getDriver(), 30)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath(xpathTextHeader)));

        System.out.println("url open_OK");
    }

    public void setCheckboxFilial(boolean value) throws InterruptedException,WebDriverException {
        checkBox(checkboxFilial, getElement(labelFilial), value);
        Thread.sleep(10000);

        if (value) {
            Assert.assertTrue("Филиалы не отображаются!", checkClassResult(CLASS_FILIAL_NAME));
            Allure.LIFECYCLE.fire(new AddParameterEvent("Филиалы", "Выбраны и отображены на странице"));
        }
        else {
            Assert.assertFalse("Филиалы отображаются!", checkClassResult(CLASS_FILIAL_NAME));
            Allure.LIFECYCLE.fire(new AddParameterEvent("Филиалы", "Отключены и не отображены на странице"));
        }
        System.out.println("setCheckboxFilial_OK");
    }

    public boolean checkClassResult(String className) {
        for(WebElement element : listItemResult) {
            if(element.getAttribute("class").contains(className))
                return true;
        }

        return false;
    }

    public void checkLocations() {
        WebElement lastElement = null;
        for(WebElement element : listDistanceResult) {
            if(lastElement!=null) {
                Assert.assertTrue("Пред. расстояние (" + getMetreDistance(lastElement.getText()) + ", больше чем текущее("
                                + getMetreDistance(element.getText()) + ")",
                        getMetreDistance(lastElement.getText()) <= getMetreDistance(element.getText()));
            }
            System.out.println("\t"+getMetreDistance(element.getText()));
            Allure.LIFECYCLE.fire(new AddParameterEvent("Расстояние до объекта", new Float(getMetreDistance(element.getText())).toString()));
            lastElement = element;
        }
        Allure.LIFECYCLE.fire(new AddParameterEvent("Все объекты находятся в порядце возврастания", "Проверено"));
        System.out.println("checkLocations_OK");
    }

    private float getMetreDistance(String text) {
        String dist = text.substring(14, text.length()-7);
        float value = new Float(dist.substring(0, dist.length()-2).trim());

        if(dist.contains("км"))
            value *= 1000;

        return value;
    }

    public void setCheckboxTerminal(boolean value) throws InterruptedException {
        checkBox(checkboxTerminal, getElement(lableTerminal), value);
        Thread.sleep(2000);
        Assert.assertTrue("Терминалы не отображаются!", checkClassResult(CLASS_TERMINAL_NAME));
        Allure.LIFECYCLE.fire(new AddParameterEvent("Терминалы", "Выбраны и отображены на странице"));

        System.out.println("setCheckboxTerminal_OK");
    }

    public void showMoreResult() throws InterruptedException {
        int beforeSize = listDistanceResult.size();
        click(getElement(xpathBtnShowMore));
        Thread.sleep(2000);
        int afterSize = listDistanceResult.size();
        Assert.assertTrue("Количество элементов в результате не изменилось", beforeSize<afterSize);
        Allure.LIFECYCLE.fire(new AddParameterEvent("Кнопка 'Показать еще' нажата, количество элементов изменилось",
                "До: " + beforeSize + ", после: " +afterSize));
        checkLocations();

        System.out.println("showMoreResult_OK");
    }
}
