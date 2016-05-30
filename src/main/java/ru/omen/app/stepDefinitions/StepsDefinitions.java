package ru.omen.app.stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import lib.Init;
import org.openqa.selenium.*;
import ru.omen.app.pages.TravelInsurancePage;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.AddParameterEvent;


/**
 * Created by OmeN on 18.05.2016.
 */
public class StepsDefinitions {

    private WebDriver driver;
    private TravelInsurancePage page;

    static final String PATH_AVG_BLOCK = "//div[div[text()='Достаточная']]";

    @Before
    public void setUp() {
        Init.initProperties();
        this.driver = Init.getDriver();
    }

    @Given("^Открыть страницу, нужный текст отображен$")
    public void openURL() {
        System.out.println("-------------STEP 1--------------");
        driver.get(Init.getProperty("url.test1").toString());
        System.out.println("openURL_OK");
        page = new TravelInsurancePage();
        Allure.LIFECYCLE.fire(new AddParameterEvent("Страница " + Init.getProperty("url.test1").toString(), "Открыта"));
    }

    @Given("^Проверить значения по умолчанию$")
    public void check_def_values() throws InterruptedException {
        System.out.println("-------------STEP 2--------------");
        page.checkDefValues();
        Allure.LIFECYCLE.fire(new AddParameterEvent("Значения", "Проверены"));
    }

    @Given("^Проверить доступность вкладов 'Оформление' и 'Подтверждение'$")
    public void check_clickable_tab() {
        System.out.println("-------------STEP 3--------------");
        page.checkClickableTab();
        Allure.LIFECYCLE.fire(new AddParameterEvent("Вкладки 'Оформление' и 'Подтверждение'", "Недоступна для нажатия"));
    }

    @When("^Итоговая стоимость должна быть примерно \"([^\"]*)\", допускается разность \"([^\"]*)\"$")
    public void check_sum(float sum, float diff) throws InterruptedException {
        System.out.println("-------------STEP 4--------------"); // 850.26f, 50.0f
        page.checkSumm(sum, diff);
        Allure.LIFECYCLE.fire(new AddParameterEvent("Итоговая стоимость должна быть: 850.26 +/- 50", new Float(sum).toString()));
    }

    @Given("^Выбрать блок 'Достаточная' в блоке 'Выберите сумму страховой защиты'$")
    public void select_block_avg() {
        System.out.println("-------------STEP 5--------------");
        page.click(By.xpath(PATH_AVG_BLOCK)); // step 5
        Allure.LIFECYCLE.fire(new AddParameterEvent("Блок 'Достаточная'", "Выбран"));
    }

    @When("^Теперь 'Итоговая стоимость' должна быть примерно \"([^\"]*)\", допускается разность \"([^\"]*)\"$")
    public void check_sum_after_select_new_block(float sum, float diff) throws InterruptedException {
        System.out.println("-------------STEP 6--------------"); // 1145.02f, 50.0f
        page.checkSumm(sum, diff); // step 6
        Allure.LIFECYCLE.fire(new AddParameterEvent("Итоговая стоимость должна быть: 1145.02 +/- 50", new Float(sum).toString()));
    }

    @Given("^В секции 'Рекомендуем предусмотреть' выбрать блок 'Спортивный' и проверить 'Итоговая стоимость'$")
    public void select_new_block() throws InterruptedException {
        System.out.println("-------------STEP 7--------------");
        page.selectSportBlock();
        Allure.LIFECYCLE.fire(new AddParameterEvent("Блок 'Спортивный' выбран", "Итоговая стоимость верная"));
    }

    @Given("^Проверить текст блока Спортивный в секции Рекомендуем предусмотреть$")
    public void check_text_values() {
        System.out.println("-------------STEP 8--------------");
        page.checkTextValue();
        Allure.LIFECYCLE.fire(new AddParameterEvent("Текстовые значения блока 'Спортивный'", "Проверены"));
    }

    @Given("^Выбрать дополнительно Предусмотрительный и проверить значение Итоговая стоимость$")
    public void select_prov_block() throws InterruptedException {
        System.out.println("-------------STEP 9--------------");
        page.selectProvidentBlock();
        Allure.LIFECYCLE.fire(new AddParameterEvent("Блок 'Предусмотрительный' выбран", "Итоговая стоимость верная"));
    }

    @Given("^Выбрать дополнительно Защита багажа, отключить значение Спортивный и проверить значение Итоговая стоимость$")
    public void select_protect_block() throws InterruptedException {
        System.out.println("-------------STEP 10--------------");
        page.selectProtectBag();
        Allure.LIFECYCLE.fire(new AddParameterEvent("Блок ' Защита багажа' выбран", "Итоговая стоимость верная"));
    }

    @After
    public void afterTest() {
        //driver.quit();
        System.out.println("Test completed!");
        Allure.LIFECYCLE.fire(new AddParameterEvent("Тест", "Выполнен"));
    }
}
