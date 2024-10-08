# SwiftRentals - Апликација за изнајмување возила
**Проект по Електронска и мобилна трговија**
---
**211198 - Филип Насковски**

[**Линк до видео**](https://youtu.be/Ylqarsi3w7A)

### Опис

Апликација за управување со резервации за изнајмување возила. Наменета за 2 типа на корисници: клиент и вработен.

* Клиентот може да разгледува возила низ различни локации, и да резервира возило.
* Вработениот управува со резервациите:
  * Креира резервација за клиент - доколку тоа не го направил веќе клиентот
  * Започнува резервација - само за резервации на локацијата на која е тој вработен (Оваа акција претставува предавање на возилото на клиентот)
  * Завршува резервација - може било која резервација, со што се ажурира новата локација на возилото (Оваа акција претставува враќање на возилото на некоја локација)
  * Откажува резервација - важи само за резервации кои не се започнати
 
Апликацијата следи *DDD принципи* со добро дефинирани модули - ограничени контексти Reservation, User, Vehicle, и Location Management.
Kafka настаните се користат за пропагирање на промени низ модулите.
   

### Tech Stack

* SpringBoot - Java
* Kafka
* Docker
* ReactJS
* JWT Token
* TailwindCSS
