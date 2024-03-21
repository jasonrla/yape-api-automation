# Título del Proyecto: Pruebas de la API Restful Booker

## Descripción
Este proyecto contiene pruebas automatizadas para la API Restful Booker. Las pruebas están escritas en Java y utilizan el marco de trabajo TestNG para estructurar y ejecutar las pruebas. La biblioteca RestAssured se utiliza para hacer solicitudes HTTP a la API.

## Herramientas y Tecnologías
- **Lenguaje**: Java
- **Marco de Pruebas**: TestNG
- **Solicitudes HTTP**: RestAssured
- **Herramienta de Construcción**: Maven
- **CI/CD**: El proyecto incluye una configuración de pipeline de CI.

## Requisitos
- Kit de Desarrollo de Java (JDK) 8 o superior
- Maven 3.6.0 o superior

## Configuración
1. Clona el repositorio en tu máquina local.
2. Navega al directorio del proyecto.
3. Ejecuta `mvn clean install` para descargar las dependencias y compilar el proyecto.

## Ejecución de Pruebas
Para ejecutar las pruebas, utiliza el siguiente comando de Maven:

```bash
mvn test
```
## Demostración

Para ver una demostración de cómo se ejecutan las pruebas, puedes ver el siguiente video: [Enlace al video](https://drive.google.com/file/d/13Iecj9Zek9dJ6ZOYhkqt2Tl81-qMdRwt/view?usp=sharing).

## Pipeline de CI/CD
El proyecto incluye un pipeline de CI que ejecuta automáticamente las pruebas cada vez que se realizan cambios en el repositorio. La configuración de este pipeline se encuentra en el directorio `.github/workflows`.

## Estructura de las Pruebas
Las pruebas están organizadas en diferentes clases, cada una representando un endpoint diferente de la API Restful Booker. Cada clase contiene métodos para diferentes escenarios de prueba. Los datos de prueba se proporcionan utilizando la anotación `@DataProvider` en TestNG.
