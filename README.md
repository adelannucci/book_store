# Book Store App

Português
- A Livraria consulta livros utilizando a Google Book API e mostra os resultados para o usuario em um Recycler view.
- A busca dos livros é paginada onde o tamanho de cada pagina contem 20 livros.
- É utilizado o PageListAdapter para consumir a API de forma paginada e integrado com o adapter.
- Ao clicar em um livro temos a tela de detalhes onde é possivel visualizar os botões para comprar e/ou adicionar o livro aos favoritos.
- Ao adicionar o livro aos favoritos o livro é salvo na base de dados do app utilizando room e na tela de detalhes do livro é possivel remolo dos favoritos
- O app contem um menu lateral para navegar entre a livraria e os favoritos.
- Na toolbar temos o icone de busca onde podemos filtrar a lista de livros. 

English
- The Bookstore consults books using the Google Book API and shows the results to the user in a Recycler view.
- The search for books is paged where the size of each page contains 20 books.
- The PageListAdapter is used to consume the API in a paginated way integrated with the adapter.
- When clicking on a book we have the details screen where you can view the buttons to buy and/or bookmark the book.
- When adding the book to the bookmarks the book is saved in the app database using room and in the book details screen it is possible to remove the bookmarks
- The app contains a side menu to navigate between the bookstore and favorites.
- In the toolbar we have the search icon where we can filter the list of books.
