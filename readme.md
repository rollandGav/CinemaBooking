# CinemaBooking — README

**Proiect:** CinemaBooking (API REST pentru rezervări cinema)

**Scop:** aplicație demo pentru predare/învățare: Spring Boot, Spring Data JPA, Hibernate, Spring Security, DTO-uri, teste unitare și de integrare, OpenAPI/Swagger.

---

## Descriere scurtă
CinemaBooking este un backend REST care permite gestionarea de filme, săli/cinema rooms, locuri (seats), proiecții (screenings) și rezervări (bookings). Aplicatia include autentificare (HTTP Basic) și autorizare pe roluri (`ROLE_USER`, `ROLE_ADMIN`) — conturi demo sunt create la pornire.

Acoperă concepte esențiale Java/Spring (controllers, services, repositories, DTO mapping, transactions, scheduling, security, testing).

---

## Funcționalități principale
- CRUD Filme (Movie)
  - GET public: listă filme, film după id
  - CREATE/UPDATE/DELETE: doar ADMIN
- Gestionare Săli (CinemaRoom)
  - Creare sală + generare automată a locurilor (seats)
  - Listare săli, detalii sală
- Locuri (Seat)
  - Listare locuri pe sală
- Proiecții (Screening)
  - Creare screening (legat de film + sală)
  - Listare screening-uri, detalii
- Rezervări (Booking)
  - Creare hold (ONHOLD) pentru set de locuri (conflict check)
  - Confirmare rezervare (CONFIRMED)
  - Anulare rezervare (CANCELLED)
  - Curățare automată (scheduler) pentru hold-uri expirate
- Users
  - Registrare publică (înregistrare simplă), obținere user curent
- Securitate
  - Autentificare HTTP Basic + BCrypt
  - Roluri: `ROLE_USER`, `ROLE_ADMIN`
  - Exemple de endpoint-uri protejate: `/api/bookings/**` (auth required), `/api/movies` (POST/PUT/DELETE -> ADMIN)
- Documentație OpenAPI (Swagger UI)

---

## Pachete / Structură (rezumat)
- `com.example.CinemaBooking.model.entities` — entități JPA (Movie, Screening, CinemaRoom, Seat, Booking, User)
- `com.example.CinemaBooking.repo` — Spring Data JPA repositories
- `com.example.CinemaBooking.service` — business logic (servicii)
- `com.example.CinemaBooking.controller` — REST controllers
- `com.example.CinemaBooking.dto` — DTO-uri pentru request/response
- `com.example.CinemaBooking.security` — config Spring Security + UserDetailsService
- `com.example.CinemaBooking.config` — DataLoader, Swagger/OpenAPI config

---

## Tehnologii & Dependențe (principale)
- Java 21
- Spring Boot (3.x)
- Spring Web
- Spring Data JPA
- Hibernate ORM
- Spring Security
- springdoc-openapi (Swagger UI)
- H2 (in-memory) — implicit pentru dezvoltare/tests
- Lombok
- JUnit5, Mockito, spring-security-test (pentru teste)

Exemplu de dependențe din `pom.xml` (rezumat):
- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `springdoc-openapi-starter-webmvc-ui`
- `h2` (runtime)
- `lombok`
- `spring-boot-starter-test` + `spring-security-test`

---

## Setări inițiale & conturi demo
La pornire, `DataLoader` inseră automat două conturi (dacă nu există):
- **admin@example.com** / **admin123** — ROLE_ADMIN
- **user@example.com** / **user123** — ROLE_USER

Poți modifica parolele/cred by editing `DataLoader` sau registrând un user (`POST /api/users/register`).

---

## Cum rulezi proiectul (local)
1. Clonează repo sau deschide proiectul în IDE (IntelliJ/Eclipse).
2. Asigură-te că ai Java 21 instalat și Maven configurat.
3. Setări DB: implicit H2 în memoria (development). Dacă vrei PostgreSQL/MySQL, modifică `application.properties`.
4. Build & run:
```bash
mvn clean package
mvn spring-boot:run
```
5. Accesează API: `http://localhost:8080` (port implicit 8080)

---

## Endpoints & cum să le testezi (Postman / curl / Swagger)
### Swagger UI
- După pornire: `http://localhost:8080/swagger-ui.html` sau `http://localhost:8080/swagger-ui/index.html` — interfață pentru a explora API-urile.

### Autentificare
- Folosește HTTP Basic (username = email, password = parola). Exemplu admin: `admin@example.com` / `admin123`.
- În Postman: Authorization → Basic Auth → completează username/password.

### Exemple rapide (Postman / curl)
#### Înregistrare user (public)
`POST /api/users/register`
Body JSON:
```json
{ "name": "Marcel", "email": "marcel@example.com", "password": "pass" }
```

#### Obține user curent (authenticated)
`GET /api/users/me` — trebuie Basic Auth.

#### Obține toate filmele (public)
`GET /api/movies`

#### Creare film (admin only)
`POST /api/movies` — folosește admin credentials.
Body JSON exemplu:
```json
{ "title": "Interstellar", "releaseYear": 2014, "rating": 9.0 }
```

#### Creează sală (admin or as allowed)
`POST /api/rooms` body:
```json
{ "name": "Room 1", "rowsCount": 5, "colsCount": 8 }
```
Vezi apoi: `GET /api/seats/room/{roomId}`

#### Creează rezervare (authenticated required)
`POST /api/bookings/screenings/{screeningId}` body:
```json
{ "userId": 2, "seatIds": [5,6] }
```
Response: `BookingResponseDto` cu status `ONHOLD`.

#### Confirmare rezervare
`POST /api/bookings/{id}/confirm` (authenticated)

---

## Testare
### Unit tests
- Rulează `mvn test` sau din IDE. Exemple incluse: teste pentru `BookingService` (succes/conflict), `CinemaRoomService`.

### Integration tests
- Sunt incluse teste care pornesc context Spring (MockMvc + H2). Unele teste folosesc `@AutoConfigureMockMvc(addFilters = false)` pentru a dezactiva securitatea temporar.
- Adaugă `spring-security-test` în `pom.xml` pentru testele care implică autentificare MockMvc.

### Comenzi utile
```bash
# rulează toate testele
mvn test
# build si run
mvn clean package
mvn spring-boot:run
```

---

## Securitate & bune practici
- În demo am folosit HTTP Basic + BCrypt pentru simplitate. Pentru producție prefer JWT sau OAuth2.
- Nu expune parolele în răspunsuri JSON (codul setează `password = null` la returnarea userului).
- Adaugă `ControllerAdvice` pentru a uniformiza erorile.

---

## Extensii posibile
- Integrare plăți (checkout) și rezervare plătită.
- Sistem de locuri VIP / prețuri dinamice.
- Retry/locking mai avansat pentru conflict seat allocation.
- Frontend (React/Angular) + securitate JWT.

