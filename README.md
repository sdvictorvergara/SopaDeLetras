# 🔤 Sopa de Letras — MVC · CRUD · GUI Swing · SQLite

Aplicación de sopa de letras desarrollada en **Java** como proyecto universitario. Permite gestionar un listado de palabras almacenadas en una base de datos local y generar sopas de letras automáticamente a partir de ellas.

---

## ✨ Funcionalidades

- **Añadir y eliminar palabras** en la base de datos desde la interfaz gráfica
- **Consultar** en tiempo real la lista de palabras almacenadas
- **Validación de entrada**: campo no vacío, sin espacios y máximo 20 caracteres
- **Generación automática** de la sopa de letras con las palabras guardadas
- Palabras colocadas en **2 orientaciones**: horizontal y vertical
- Casillas vacías rellenas con **letras aleatorias** del abecedario español (incluye Ñ)

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| **Java** | Lenguaje principal de la aplicación |
| **Java Swing** | Interfaz gráfica (GUI) |
| **SQLite** | Base de datos local para persistir las palabras |
| **sqlite-jdbc 3.53.1.0** | Driver JDBC para conectar Java con SQLite |
| **Apache NetBeans** | IDE utilizado para el desarrollo |

---

## 🏗️ Arquitectura — Patrón MVC

El proyecto sigue el patrón **Modelo-Vista-Controlador**:

```
src/
├── Main.java                        # Punto de entrada
├── Controlador/
│   ├── ControladorSopa.java         # Gestiona eventos y coordina Modelo y Vista
│   └── SopaLetras.java              # Lógica de generación de la sopa (matriz 20×20)
├── Modelo/
│   └── ModeloSopa.java              # Acceso a la BD SQLite (CRUD de palabras)
└── Vista/
    ├── VentanaPrincipal.java        # Ventana principal (JFrame)
    └── VentanaPrincipal.form        # Formulario NetBeans GUI Builder
```

---

## 🗄️ Base de datos

La base de datos **sopa_letras.db** (SQLite) se crea automáticamente al iniciar la aplicación. Contiene la siguiente tabla:

- **palabras** — almacena cada palabra con un id autoincremental y la restricción `UNIQUE` para evitar duplicados

Las palabras se convierten a mayúsculas antes de ser guardadas para normalizar las búsquedas.

---

## ▶️ Cómo ejecutar

### Opción 1 — JAR precompilado

```bash
java -jar dist/SopaDeLetras.jar
```

### Opción 2 — Desde NetBeans

1. Abre NetBeans y selecciona **File → Open Project**
2. Navega a la carpeta del proyecto y ábrelo
3. Pulsa **Run** (F6)

> El archivo `sopa_letras.db` se generará automáticamente en el directorio raíz del proyecto la primera vez que se ejecute.

---

## 📦 Dependencias

- [sqlite-jdbc 3.53.1.0](https://github.com/xerial/sqlite-jdbc) — incluida en `lib/`

No se necesita ninguna instalación adicional de base de datos.
