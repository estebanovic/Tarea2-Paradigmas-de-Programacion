
# Sistema de Pruebas - Java Swing

Aplicación de escritorio desarrollada en Java Swing para la creación, aplicación y revisión de pruebas académicas, con clasificación de preguntas según la taxonomía de Bloom.

---

##  Cómo ejecutar

1. Descargue o clone este repositorio.
2. Ábralo en NetBeans.
3. Asegúrese de tener una base de datos MySQL local activa.
4. Configure la conexión en el archivo `Conexion.java`, editando los siguientes parámetros:

```java
String sqlUser = "root";
String sqlPass = "root";
String driver = "com.mysql.cj.jdbc.Driver";
String conRoute = "jdbc:mysql://localhost:3306/sistema_pruebas";
```

>  Ajuste los valores de usuario, contraseña y nombre de base de datos según su configuración local.

---

##  Estructura de la base de datos

Antes de ejecutar la aplicación, cree las siguientes tablas en su base de datos MySQL:

```sql
CREATE TABLE pruebas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL
);

CREATE TABLE preguntas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prueba_id INT,
    enunciado TEXT NOT NULL,
    tipo ENUM('VF', 'MULTIPLE'),
    nivel_bloom VARCHAR(20),
    FOREIGN KEY (prueba_id) REFERENCES pruebas(id) ON DELETE CASCADE
);

CREATE TABLE opciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pregunta_id INT,
    texto TEXT,
    es_correcta BOOLEAN,
    FOREIGN KEY (pregunta_id) REFERENCES preguntas(id) ON DELETE CASCADE
);

CREATE TABLE resultados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prueba_id INT NOT NULL,
    fecha DATETIME NOT NULL,
    nota DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (prueba_id) REFERENCES pruebas(id)
);

CREATE TABLE respuestas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    resultado_id INT NOT NULL,
    pregunta_id INT NOT NULL,
    opcion_marcada TEXT NOT NULL,
    correcta BOOLEAN NOT NULL,
    FOREIGN KEY (resultado_id) REFERENCES resultados(id),
    FOREIGN KEY (pregunta_id) REFERENCES preguntas(id)
);
```

---

##  Funcionalidades principales

- Creación de pruebas desde la interfaz gráfica
- Preguntas de selección múltiple o verdadero/falso
- Aplicación de pruebas una pregunta a la vez
- Cálculo de nota en escala de 1 a 7
- Revisión de resultados con desglose por tipo y nivel Bloom
- Consulta de resultados anteriores

---

 La funcionalidad de carga desde archivo fue reemplazada por la creación directa de pruebas desde la interfaz, según lo autorizado por el profesor responsable del curso.
