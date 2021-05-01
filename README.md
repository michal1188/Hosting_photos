# Hosting Zdjęć

## **Opis aplikacji**

Stworzona aplikacja zajmuje  się hostingiem zdjęć. Program  posiada 2 rodzaje użytkowników (administrator oraz user), których będzie przechowywać baza danych. Po zalogowaniu administrator ma możliwość  dodawania, edytowania  oraz  usuwania zdjęć, a ponadto dodawania oraz  usuwania galerii .Zdjęcia będą przypisywane do konkretnych galerii. Po zalogowaniu użytkownik ma możliwość przeglądania zdjęć w dostępnych galeriach, ponadto użytkownik ma możliwość dodawania komentarzy. Serwis jest skierowany do użytkowników chcących dodawać i pogrupować pliki graficzne .Nie zalogowany użytkownik ma  możliwość oglądania zdjęć i dodawania komentarzy. Nowych użytkowników dodaje tylko programista.

## **Technologie**

 - Java
 - Spring
 - Hibernate
 - Vaadin
 - SQL



## **Endpointy**

**### Uwierzytelnianie :**

- logowanie POST  http://localhost:8080/login

DANE WEJSCIOWE:

```

{

"username": "[nazwa użytkownika (string)]",

"password": "[hasło (string)]"

}

```

- wylogowanie

**### Zdjęcia:**

- dodawanie zdjęcia Metoda : POST http://localhost:8080/admin/dodaj_obrazek

```

Wymagana autoryzacja: TAK

Wymagane uprawnienia administratora: TAK

```

- wyświetlanie zdjęć Metoda : GET http://localhost:8080/admin/ListaZdjec

```

Wymagana autoryzacja: TAK

Wymagane uprawnienia administratora: TAK

```

- edycja zdjęcia  PUT http://localhost:8080/admin/ListaZdjec

```

Wymagana autoryzacja: TAK

Wymagane uprawnienia administratora: TAK

```

- usuwanie zdjęcia DELETE http://localhost:8080/admin/ListaZdjec

```

Wymagana autoryzacja: TAK

Wymagane uprawnienia administratora: TAK

```

**### Galeria:**

- dodawanie galerii POST http://localhost:8080/admin/dodaj_galerie

```

Wymagana autoryzacja: TAK

Wymagane uprawnienia administratora: TAK

```

- wyświetlanie galerii GET http://localhost:8080/admin/lista_galerii

```

Wymagana autoryzacja: TAK

Wymagane uprawnienia administratora: NIE

```

- usuwanie galerii (admin) DELETE http://localhost:8080/admin/gallery

```

Wymagana autoryzacja: TAK

Wymagane uprawnienia administratora: TAK

```

- wyświetlanie jednego zdjęcia GET http://localhost:8080/user/photo

```

Wymagana autoryzacja: NIE

Wymagane uprawnienia administratora: NIE

```

- dodawanie komentarza POST http://localhost:8080/admin/photo

```

Wymagana autoryzacja: NIE

Wymagane uprawnienia administratora: NIE

```

- wyświetlanie jednej galerii GET http://localhost:8080/user/gallery

```

Wymagana autoryzacja: NIE

Wymagane uprawnienia administratora: NIE

