# my_life_service
## Сервис для контроля ежедневных задач, финансов, кредитной нагрузки, обучающих курсов и другого.
***
[<<< Фронт-приложение >>>](https://github.com/ekip-s/my_life_service_front)
***
#### Сервисы:
| Сервис:          | Swagger                                                | Порт: | Описание:                             |
|------------------|--------------------------------------------------------|-------|---------------------------------------|
| person_maser     | [Swagger](http://localhost:8080/swagger-ui.html)       | 8080  | Сервис работает с сущностью Person.   |
| credit_master    | [Swagger]()                                            |       |                                       |
| courses_master   | [Swagger](http://localhost:8081/swagger-ui/index.html) | 8081  | Сервис работает с обучающими курсами. |
| exception_master | -                                                      | -     | Сервис обработки исключений.          |
| model_master     | -                                                      | -     | Хранилище моделей.                    |
| person_client    | -                                                      | -     | Общая логика для коннекта с Person    |

***
### updates list:

***
30.09.2023:
 - `person_maser`: перенес репозитарии из model_master; 
 - `model_master`: перенес репозитарии в целевые сервисы; 
 - `person_client`: новый сервис person_client для общей логики коннекта с person_maser; 
 - `credit_master`:
    - перенес репозитарии из model_master; 
***
