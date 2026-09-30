# Taller Práctico – Bloque 5

**De clases aisladas a objetos que colaboran**
Interfaces · Asociación · Agregación · Composición · Herencia · Componentes UML

Caso conductor: **SmartLibrary**

---

## Contenido del repositorio

```
taller practico bloque 5/
├── src/                  Código Java de la Actividad 5
│   ├── Notificable.java
│   ├── Usuario.java
│   ├── Estudiante.java
│   ├── Bibliotecario.java
│   ├── Libro.java
│   ├── Ejemplar.java
│   ├── Renovacion.java
│   ├── Prestamo.java
│   └── PruebaSmartLibrary.java
├── docs/                 Código PlantUML de los diagramas
│   ├── SmartLibrary_DiagramaClases.puml
│   ├── SmartLibrary_HerenciaEInterfaz.puml
│   └── SmartLibrary_Componentes.puml
└── README.md
```

---

## Cómo ejecutar

```bash
javac -d bin src/*.java
java -cp bin PruebaSmartLibrary
```

---

## Decisiones de diseño

### Relaciones estructurales

| Relación | Tipo | Justificación |
|---|---|---|
| `Estudiante – Prestamo` | **Asociación** (1 → 0..\*) | El estudiante existe antes del préstamo y el préstamo debe conservarse como historial aunque el estudiante se gradúe. |
| `Prestamo – Ejemplar` | **Asociación** (0..\* → 1) | El préstamo usa el ejemplar temporalmente; el ejemplar vuelve a la estantería al devolverlo. |
| `Libro – Ejemplar` | **Composición** (1 ◆→ 0..\*) | R10: cada ejemplar corresponde a un único libro y no puede reasignarse. Sin el libro, el ejemplar no tiene identidad. |
| `Prestamo – Renovacion` | **Composición** (1 ◆→ 0..\*) | R11: la renovación es un registro interno del préstamo. Sus tres fechas solo significan algo referidas a él. |

**Supuesto declarado para `Libro – Ejemplar`:** SmartLibrary no mantiene ejemplares
huérfanos sin libro catalogado. Si se asumiera que la biblioteca puede conservar un
ejemplar físico mientras se corrige su ficha, la relación correcta sería **agregación**.

### Herencia

`Usuario` es **abstracta**: en SmartLibrary nadie es "solo usuario". La generalización
se justifica por la prueba de sustitución —un estudiante o un bibliotecario pueden
ocupar el lugar de un usuario sin romper el significado del modelo— y **no** por los
tres atributos repetidos de R12.

### Interfaz

`Notificable` lo implementan `Estudiante` y `Bibliotecario`, **nunca** `Usuario`.
La razón es R13: "**algunos** usuarios pueden recibir notificaciones". Declararlo en
`Usuario` afirmaría que todo usuario es notificable, lo que contradice el requisito.

---

## Cómo el código hace cumplir el UML

Una composición no se demuestra con una lista. En este proyecto se hace cumplir con
tres mecanismos combinados:

1. `Ejemplar` y `Renovacion` **no tienen constructor público**: solo pueden nacer desde
   `Libro.registrarEjemplar()` y `Prestamo.renovar()` respectivamente.
2. Las referencias al todo son **`final`**: nunca se reasignan a otro padre.
3. Las colecciones se devuelven con `Collections.unmodifiableList()`: nadie inserta ni
   elimina partes desde fuera del todo.

`Prestamo.renovar()` valida **antes** de modificar cualquier cosa, por lo que la
operación es atómica: una renovación inválida deja el préstamo exactamente igual y no
contamina el historial.

---

## Alcance

Este bloque se limita a relaciones estructurales y contratos de comportamiento.
No se aplican GRASP, SOLID ni patrones de diseño, que corresponden al bloque siguiente.
