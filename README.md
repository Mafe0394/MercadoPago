# MercadoPago
## Challenge MercadoPago
Ésta aplicación fue desarrollada en Android Studio utilizando kotlin y siguiendo los principios de [arquitectura limpia](https://developer.android.com/jetpack/guide#recommended-app-archz) sugeridos por Google. La aplicación usa viewModels con fragmentos, un repositorio y Room. Además, utiliza un Navigation Component para navegar entre fragmentos e inyección de dependencias con Hilt-Dagger, que separa nuestro código en módulos, cada uno con una única función que facilita la creación de test unitarios.

![Architecture Diagram](https://user-images.githubusercontent.com/37948478/134438494-c9f1e250-6c5a-4f1b-810b-d625e0e8ca1f.png)

La aplicación cuenta con tres pantallas:
1. **Pantalla de búsqueda:** está compuesta por un SearchView embevido en el Appbar que a través de un texto nos permite retornar una lista de productos en la patalla de resultado una vez es accionado el boton de "submit". Además, cuenta con un recyclerView para visualizar los últimos productos vistos por el usuario los cuales se encuentran guardados en el cache del dispositivo y un botón para borrar el historial de productos vistos.
2. **Pantalla de resultados:** está compuesta por un recyclerView que muestra la lista de productos arrojada por una búqueda desde la [API de MercadoPago](https://developers.mercadolibre.com.ar/es_ar/items-y-busquedas). Para los request se utilizó Retrofit junto con Moshi, los cuales nos permiten interactuar con el servicio y convertir la respuesta de un formato Json a un modelo en Kotlin.
3. **Pantalla de detalles:** permite una visualización de un producto en específico junto con su descripción e imagenes, las cuales son mostradas a través de un ViewPager. La información es obtenida a través de Retrofit y cada vez que un producto es visitado, la aplicación hace un llamado a la base de datos para guardar el detalle del producto y luego mostrarlo en la pantalla de búqueda.

A continuación se muestra un resument de los paquetes que componen la aplicación:
|**Package:** | com.projects.mercadopago|
|----------|---------------------------|
|.adapters | Los adaptadores utilizados para configuar los recyclerView y el viewPager|
|.app| Contiene la aplicación base, en el método onCreate de esta clase se ejecutan los procesos antes de que la primera pantalla sea visible al usuario, está definida en el androidManifest y es el punto de partida para la inyección de dependencias con Hilt-Dagger|
|.data| Maneja toda la información de la aplicación. Contiene el código que configura la base de datos, la red y el repositorio. Además, contiene la capa "domain" encargada de convertir tanto la respuesta del servicio como de la base de datos en un modelo que pueda ser fácilmente utilizada en la aplicación.|
|.di|Contiene los módulos para la inyección de dependencias con Hilt-Dagger. Éstos módulos son los encargados de decirle a Hilt como deben ser inicializadas las dependencias.|
|.uiControllers| Los fragments y la actividad que componen la aplicación.|
|.util| Utility classes, clases compartidas y usadas en varias partes de la aplicación|
|.viewModels| Los ViewModel encargados manejar los datos de la aplicación obtenidos a través del repositorio.|

La clase **MainActivity**  es la actividad que contiene todos los fragments. La navegación entre fragmentos es controlada por un [Navigation component](https://developer.android.com/guide/navigation). Éste está definido en el archivo **navigation.xml**.

![WhatsApp-Video-2021-09-22-at-110](https://user-images.githubusercontent.com/37948478/134449487-02b95874-afb3-4942-b0e2-b1dde86fe3d5.gif)


