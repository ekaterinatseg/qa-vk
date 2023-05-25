# Автоматизированный тест для сайта VK.COM с использованием Selenium и Aquality Framework

## Описание тест кейсов:
1. [VkTest.java](src%2Ftest%2Fjava%2Forg%2Ftsegelnikova%2FVkTest.java)
   1. Перейти на главную страницу vk.com --> открылась форма авторизации;
   2. Авторизоваться: ввести логин, пароль --> успешная авторизация;
   3. В левом меню кликнуть кнопку "Моя страница" --> открылась страница пользователя;
   4. С помощью api запроса создать новый пост со случайно сгенерированным текстом --> получить id записи;
   5. Проверить, что текст в посте соответствует случайно сгенерированному --> тексты совпадают;
   6. Проверить, что имя пользователя в посте совпадает с именем пользователя страницы --> имена совпадают;
   7. С помощью api запроса отредактировать пост (изменить текст), добавить картинку;
   8. Проверить, что картинка в посте соответствует загруженной --> картинки идентичны;
   9. Проверить, что текст поста соответствует сгенерированному в п.7 --> тексты совпадают;
   10. С помощью api запроса оставить случайно сгенерированный комментарий к созданному посту --> комментарий добавлен;
   текст комментария соответсвует случайно сгенерировааному;
   11. С помощью api запроса оставить лайк к созданному посту --> лайк оставлен; лайк оставлен от пользователя страницы;
   12. С помощью api запроса удалить созданный пост; через ui проверить, что пост удален --> пост успешно удален;
   


## Список библиотек:
1. Selenium 
2. Aquality Framework
3. TestNg
4. Unirest 
5. Lombok
6. OpenCV