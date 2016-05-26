Feature: Suite1
  @TravelInsurance
  Scenario: Проверка расчета стоимости страховки для путешественников
    * Открыть страницу, нужный текст отображен
    * Проверить значения по умолчанию
    * Проверить доступность вкладов 'Оформление' и 'Подтверждение'
    * Итоговая стоимость должна быть примерно "850.26", допускается разность "50.0"
    * Выбрать блок 'Достаточная' в блоке 'Выберите сумму страховой защиты'
    * Теперь 'Итоговая стоимость' должна быть примерно "1145.02", допускается разность "50.0"
    * В секции 'Рекомендуем предусмотреть' выбрать блок 'Спортивный' и проверить 'Итоговая стоимость'
    * Проверить текст значения Спортивный в блоке Рекомендуем предусмотреть
    * Выбрать дополнительно Предусмотрительный и проверить значение Итоговая стоимость
    * Выбрать дополнительно Защита багажа, отключить значение Спортивный и проверить значение Итоговая стоимость

  @CurrencyConverter
  Scenario: Проверка конвертации валюты
    * Открыть страницу, на ней размещен 'Конвертер валют'
    * Проверить дату в блоке 'Конвертер валют'
    * Проверить наличие компонентов в блоке Конвертер валют
    * Установить значения в конвертере: Поменять - "RUB" = "34", НА - "EUR"
    * Проверить рассчитанное значение в поле НА
    * Установить значения в конвертере: Поменять - "USD" = "10023", НА - "EUR"
    * Проверить рассчитанное значение в поле НА
    * Установим значение в конвертере USD=5, USD. Система автоматически устанавливает в поле ПОМЕНЯТЬ значение RUB
    * Проверить рассчитанное значение в поле НА

  @SearchAtm
  Scenario: Проверка поиска ближайших банкоматов и отделений
    * Перейти по ссылке, на станице размещен текст Отделения и банкоматы
    * Выделить чекбокс Отделения, Проверить информацию в блоке Ближайшие к вам
    * Проверить порядок расположения найденных ближайших локаций
    * Отметить чекбокс Платежные устройства
    * Нажать на кнопку Показать еще. Проверить порядок расположения найденных ближайших локаций
    * Снять выделение чекбокса Отделения
    * Проверить порядок расположения найденных ближайших локаций