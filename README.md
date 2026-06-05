<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0%3A0F3D2E%2C50%3A10B981%2C100%3AD1FAE5&height=200&section=header&text=App+Tickets&fontSize=60&fontColor=fff&fontAlignY=38&desc=App+m%C3%B3vil+para+gesti%C3%B3n+de+tickets+de+soporte&descAlignY=62&descSize=18" width="100%"/>
</div>

<div align="center">
  <a href="https://github.com/evagr11/AppTickets">
    <img src="https://img.shields.io/badge/Ver_Código-10B981?style=for-the-badge&logo=github&logoColor=white" alt="Ver Código">
  </a>
  <a href="https://evagr11.github.io/Curriculum/proyectos/app-tickets.html">
    <img src="https://img.shields.io/badge/Ver_Web-0F3D2E?style=for-the-badge&logo=webr&logoColor=white" alt="Ver Web">
  </a>
</div>

  <br>
  
<div align="center">
  <a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.demolab.com/?font=JetBrains+Mono&size=18&duration=3500&pause=800&color=10B981&center=true&vCenter=true&width=600&lines=Desarrollo+nativo+en+Android%3BProgramaci%C3%B3n+con+Java%3BPersistencia+en+almacenamiento+local%3BInterfaz+con+Fragments%3BDise%C3%B1o+accesible" alt="Typing SVG" /></a>
</div>

# 🚀 Sobre el Proyecto

**App Tickets** es una aplicación móvil nativa para Android diseñada para la **gestión de incidencias y tickets de soporte**. Permite a los usuarios crear, editar, consultar y filtrar tickets de manera eficiente, con toda la información persistida localmente en el dispositivo.

<div align="center">
  <img src="https://github.com/user-attachments/assets/1d2c888d-9f6e-48cd-8479-06772db40317" alt="Mockup de la app de tickets" width="70%"/>
  <p><em>Mockup de la app de tickets</em></p>
</div>

### ✨ Características principales:

*   📱 **Gestión de Tickets:** Permite crear nuevos tickets con título, descripción y pasos para reproducir el fallo. Los tickets existentes pueden ser editados para actualizar su información o cambiar su estado.
*   💾 **Persistencia Local:** Los tickets se guardan como archivos de texto en el almacenamiento interno de la aplicación, asegurando que los datos no se pierdan al cerrar la app.
*   🔍 **Filtrado por Estado:** Un sistema de `Chips` interactivos permite al usuario filtrar la lista de tickets por su estado: `Nuevo`, `Abierto`, `En proceso`, `Resuelto` o `Cerrado`.
*   ♿ **Diseño Accesible:** La paleta de colores para los indicadores de estado fue seleccionada cuidadosamente para ser **distinguible por personas con daltonismo**, cumpliendo con pautas de contraste y usabilidad.
*   📚 **Documentación Completa:** El proyecto está acompañado de una detallada documentación técnica desarrollada en **LaTeX**.

| Pantalla Principal                                                                                      | Creación de Ticket                                                                      |
| ------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| <div align="center"> <img src="https://github.com/user-attachments/assets/da1d1afb-114a-4154-8acf-873e9a53f125" alt="Ejemplo de como se veria el home de la app con algunos tickets creados" width="60%"/> </div> | <div align="center"> <img src="https://github.com/user-attachments/assets/0ee200a1-5bb1-43a6-9a40-63158cddbbff" alt="Ejemplo de como se veria el apartado de crear un ticket" width="60%"/> </div> |
| *Vista principal con la lista de tickets.*                                                              | *Formulario para la creación de un nuevo ticket.*                                       |

# 🧠 Contexto del Proyecto

Este proyecto fue desarrollado como parte de las asignaturas **Desarrollo de Interfaces** y **Programación de Dispositivos Multimedia**, correspondientes al segundo año del ciclo formativo de **Desarrollo de Aplicaciones Multiplataforma (DAM)**. Su objetivo principal fue aplicar y consolidar los conocimientos teóricos sobre desarrollo nativo en Android, arquitectura de software (Modelo-Vista-ViewModel) y persistencia de datos.

# 🛠️ Stack Tecnológico

<div align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle">
  <img src="https://img.shields.io/badge/LaTeX-008080?style=for-the-badge&logo=latex&logoColor=white" alt="LaTeX">
  <img src="https://img.shields.io/badge/STATUS-Estable-10B981?style=for-the-badge" alt="Status">
</div>

  | Componente      | Tecnología Utilizada                                                                           |
  | --------------- | ---------------------------------------------------------------------------------------------- |
  | **Plataforma**  | `Android Nativo`                                                                               |
  | **Lenguaje**    | `Java 11`                                                                                      |
  | **Build Tool**  | `Gradle`                                                                                       |
  | **UI**          | `XML Layouts`, `Fragments`, `Material Components` (Chips, BottomNavigationView)                |
  | **Librerías**   | `AndroidX` (AppCompat, ConstraintLayout, Fragment)                                             |
  | **Persistencia**| `Sistema de archivos interno`                                                                    |
  | **Documentación**| `LaTeX`                                                                                        |


# 📂 Estructura del Proyecto

La estructura del proyecto sigue las convenciones estándar de Android Studio, organizando el código fuente en un patrón similar a MVVM (Model-View-ViewModel).

```bash
AppTickets/
├── app/
│   ├── build.gradle.kts              # Dependencias y configuración del módulo
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/apptickets/
│   │   │   │   ├── Model/            # Clases de datos (Ticket, EstadoTicket)
│   │   │   │   ├── View/             # Actividades, Fragmentos y Adapters
│   │   │   │   └── ViewModel/        # Lógica de negocio (TicketManager)
│   │   │   ├── res/
│   │   │   │   ├── layout/           # Diseños de pantalla (XML)
│   │   │   │   ├── values/           # Colores, strings, temas
│   │   │   │   └── drawable/         # Recursos gráficos
│   │   │   └── AndroidManifest.xml   # Configuración principal de la app
│   └── .gitignore
├── build.gradle.kts                  # Configuración del proyecto
└── gradlew                           # Wrapper de Gradle
```

# 🚀 Cómo Utilizar el Proyecto

Para clonar y ejecutar este proyecto, necesitarás tener **Android Studio** y el **JDK 11** (o superior) instalados.

1.  **Clona el repositorio:**

    ```bash
    git clone https://github.com/evagr11/AppTickets.git
    ```

2.  **Abre el proyecto en Android Studio:**
    *   En Android Studio, selecciona `File > Open`.
    *   Navega hasta el directorio donde clonaste el repositorio y selecciónalo.

3.  **Sincroniza Gradle:**
    *   Android Studio detectará automáticamente los archivos `build.gradle.kts` y te pedirá sincronizar el proyecto. Espera a que se descarguen todas las dependencias.

4.  **Ejecuta la aplicación:**
    *   Selecciona un emulador de Android o conecta un dispositivo físico.
    *   Haz clic en el botón `Run 'app'` (icono de play verde) en la barra de herramientas superior.

</br>

<div align="center">
  <p>Creado por <strong>Eva Gallardo</strong></p>
  <a href="https://github.com/evagr11" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub">
  </a>
  <a href="https://www.linkedin.com/in/eva-gallardo-romero-830362349/" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn">
  </a>
</div>

<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0%3A0F3D2E%2C50%3A10B981%2C100%3AD1FAE5&height=120&section=footer" width="100%"/>
</div>
