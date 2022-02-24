# KinoApp
Паттерн проектирования : MVP
Стэк: Dagger2,Retrofit,Picasso
Библиотеки: для сетевых запросов использована библиотека Retrofit.  
Использовано DI Dagger2: Inject Presenter\Retrofit  
Обложки к фильмам загружаются с помощью библиотеки Picasso.   

На экране отображается два заголовка : Жанры и Фильмы.  
У каждого фильма отображается афиша и названиие фильма.  
Отображение реализовано в одном RecyclerView с помощью sealead class.    
При клике на кнопку жанров, появятся фильмы в соответствии с типом жанра фильма.  
При повторном клике на этот же жанр сбрасывается подсветка кнопки и подгружаются все фильмы.  
При клике на фильм появится другой экрна с подробной информацией о нем.  
В полном описании фильма, есть возможность скроллить текст, если он не влазит в экран  при горизонтальном положении.  


![Kinopoisk](https://user-images.githubusercontent.com/51223584/152963779-7ddc8ac5-4a4b-4965-ac1b-e69cd5385973.jpg)
![kinopoisk1](https://user-images.githubusercontent.com/51223584/152963789-5e5612de-c9a8-4ce0-92bc-4ec74b9fd158.jpg)
![Kimopoisk3](https://user-images.githubusercontent.com/51223584/152963893-6e9ea135-6894-417b-8873-48a79c079c7c.jpg)
![horizontal1](https://user-images.githubusercontent.com/51223584/152964096-046bac6f-73a2-4a0e-9732-bf9be92455c8.jpg)
![kinopoisk5](https://user-images.githubusercontent.com/51223584/152964111-a4584f6b-5c15-4deb-88cc-7a6c0a265dff.jpg)
