# Yandex-ShareIt

## Стек

Java Core, Spring Boot, Spring Framework, Git, Maven, SQL (PostgreSQL, H2), Hibernate, JUnit, Docker.

## Для чего предназначен данный проект
Yandex-ShareIt это платформа для аренды вещей. Можно брать в аренду вещи, оставлять отзывы на вещи, которые были арендованы, создавать запросы, если нужной вещи еще нет на платформе. Реализована в виде микросервисов - один для валидации запросов, второй для остальной бизнес логики, БД тоже подключается как отдельный микросервис.

## Функциональности проекта
### Функциональности пути /bookings
**GET /bookings?state={state}&from={from}&size={size}** получение списка всех бронирований пользователя отсортированных по дате от более новым к старым, id пользователя передается в качестве заголовка "X-Sharer-User-Id". Параметр 'state' необязательный и по умолчанию равен ALL, также он может принимать значения CURRENT, PAST, FUTURE, WAITING, REJECTED, все прочие значения считаются не валидными. Параметр 'from' номер первого выводимого элемента из списка (по умолчанию = 0), параметр 'size' задает количество элементов для отображения (по умолчанию = 10). 

**GET /bookings/owner?state={state}&from={from}&size={size}** получение списка всех бронирований вещей принадлежащих пользователю отсортированных по дате от более новым к старым, id пользователя передается в качестве заголовка "X-Sharer-User-Id". Параметр 'state' необязательный и по умолчанию равен ALL, также он может принимать значения CURRENT, PAST, FUTURE, WAITING, REJECTED, все прочие значения считаются не валидными. Параметр 'from' номер первого выводимого элемента из списка (по умолчанию = 0), параметр 'size' задает количество элементов для отображения (по умолчанию = 10). 

**GET /bookings/{bookingId}** получение бронирования вещи по id, id пользователя бронирующего вещь передается в качестве заголовка "X-Sharer-User-Id". Бронирование посмотреть может только пользователь, осуществляющий бронирование или владелец вещи.

**POST /bookings** создание бронирования вещи, id пользователя создающего бронирование передается в качестве заголовка "X-Sharer-User-Id".

**PATCH /bookings/{bookingId}?approved={approved}** подтверждение или отклонение бронирования вещи пользователем, id пользователя передается в качестве заголовка "X-Sharer-User-Id".

### Функциональности пути /items
**GET /items?from={from}&size={size}** получение списка всех вещей пользователя, id пользователя передается в качестве заголовка "X-Sharer-User-Id". Параметр 'from' номер первого выводимого элемента из списка (по умолчанию = 0), параметр 'size' задает количество элементов для отображения (по умолчанию = 10).

**GET /items/{id}** получение вещи по id, id пользователя передается в качестве заголовка "X-Sharer-User-Id".

**GET /items/search?text={text}&from={from}&size={size}** получение списка вещей по текстовому запросу. Пользователь передаёт в строке запроса текст, и система ищет вещи, содержащие этот текст в названии или описании. Параметр 'from' номер первого выводимого элемента из списка (по умолчанию = 0), параметр 'size' задает количество элементов для отображения (по умолчанию = 10). Можно найти только доступные для бронирования вещи.

**POST /items** создание вещи, id пользователя создающего бронирование передается в качестве заголовка "X-Sharer-User-Id".

**POST /items/{itemId}/comment** создания отзыва на вещь, отзыв можно оставить только если пользователь брал вещь в аренду и только по оканчии аренды, id пользователя передается в качестве заголовка "X-Sharer-User-Id". 

**PATCH /items/{id}** внесение изменений пользователем в созданную вещь, id пользователя создающего бронирование передается в качестве заголовка "X-Sharer-User-Id".

**DELETE /items/{id}** удаление вещи.

### Функциональности пути /requests
**GET /requests** получение пользователем всех его запросов на создание вещей отсортированных по дате от более новым к старым, id пользователя создающего бронирование передается в качестве заголовка "X-Sharer-User-Id".

**GET /requests/all?from={from}&size={size}** получение списка запросов вещей отсортированных по дате от более новым к старым, за исключением запросов самого пользователя, id пользователя создающего бронирование передается в качестве заголовка "X-Sharer-User-Id". Параметр 'from' номер первого выводимого элемента из списка (по умолчанию = 0), параметр 'size' задает количество элементов для отображения (по умолчанию = 10).

**GET /requests/{requestId}** получение запроса по его id.

**POST /requests** создание запроса на вещь, id пользователя создающего бронирование передается в качестве заголовка "X-Sharer-User-Id".

### Функциональности пути /users
**GET /users** получение списка всех пользователей.

**GET /users/{id}** получение пользователя по его id.

**POST /users** создание пользователя.

**PATCH /users/{id}** обновление данных пользователя с id.

**DELETE /users/{id}** удаление пользователя по id.
