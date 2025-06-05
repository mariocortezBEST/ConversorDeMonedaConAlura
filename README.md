# 💱 Conversor de Monedas

Un conversor de monedas profesional desarrollado en Java que utiliza APIs en tiempo real para obtener tasas de cambio actualizadas. Implementa Clean Architecture y múltiples patrones de diseño para asegurar código mantenible y escalable.

![Java](https://img.shields.io/badge/Java-11+-orange)
![API](https://img.shields.io/badge/API-ExchangeRate--API-blue)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 🚀 Características

- ✅ **Conversión en tiempo real** entre múltiples monedas
- ✅ **Interfaz de consola intuitiva** con menú interactivo
- ✅ **Historial de conversiones** con timestamps
- ✅ **Validaciones específicas** por tipo de moneda
- ✅ **Arquitectura limpia** y escalable
- ✅ **Manejo robusto de errores**
- ✅ **Patrones de diseño profesionales**

## 🌍 Monedas Soportadas

| Código | Moneda | País |
|--------|--------|------|
| **USD** | Dólar estadounidense | Estados Unidos |
| **ARS** | Peso argentino | Argentina |
| **BRL** | Real brasileño | Brasil |
| **CLP** | Peso chileno | Chile |
| **COP** | Peso colombiano | Colombia |
| **BOB** | Boliviano boliviano | Bolivia |

## 📋 Prerrequisitos

- **Java 11** o superior
- **Gson 2.8.9** o superior
- **API Key** de [ExchangeRate-API](https://app.exchangerate-api.com/) (gratuita)

## 🛠️ Instalación

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/conversor-monedas.git
cd conversor-monedas
```

### 2. Descargar dependencias
```bash
# Crear carpeta libs
mkdir libs

# Descargar Gson JAR
wget https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.9/gson-2.8.9.jar -P libs/
```

### 3. Configurar API Key
```bash
# Editar Main.java y reemplazar "YOUR_API_KEY" con tu clave real
# Obtén tu API Key gratuita en: https://app.exchangerate-api.com/
```

### 4. Compilar
```bash
javac -cp "libs/gson-2.8.9.jar" -d out src/main/java/com/conversor/monedas/**/*.java
```

### 5. Ejecutar
```bash
# Windows
java -cp "out;libs/gson-2.8.9.jar" com.conversor.monedas.Main

# Linux/Mac
java -cp "out:libs/gson-2.8.9.jar" com.conversor.monedas.Main
```

## 🎮 Uso

### Menú Principal
```
=================================
Sea bienvenido/a al Conversor de Moneda
=================================

--- MENÚ PRINCIPAL ---
1) Dólar =>> Peso argentino
2) Peso argentino =>> Dólar
3) Dólar =>> Real brasileño
4) Real brasileño =>> Dólar
5) Dólar =>> Peso colombiano
6) Peso colombiano =>> Dólar
7) Conversión personalizada
8) Ver historial de conversiones
9) Limpiar historial
0) Salir
Elija una opción válida:
```

### Ejemplo de Conversión
```
Ingrese el valor que desea convertir de USD: 100
El valor 100.00 [USD] corresponde al valor final de =>>> 36,850.00 [ARS]
```

### Historial
```
--- HISTORIAL DE CONVERSIONES ---
1. [04/06/2025 15:30:25] 100.00 USD → 36850.00 ARS (Tasa: 368.5000)
2. [04/06/2025 15:28:10] 50.00 ARS → 0.14 USD (Tasa: 0.0027)
```

## 🏗️ Arquitectura

El proyecto sigue **Clean Architecture** con separación clara de responsabilidades:

```
src/main/java/com/conversor/monedas/
├── 📁 domain/           # Lógica de negocio pura
│   ├── entities/        # Entidades del dominio
│   ├── repositories/    # Contratos de persistencia
│   ├── valueobjects/    # Objetos de valor
│   └── exceptions/      # Excepciones del dominio
├── 📁 application/      # Casos de uso y servicios
│   └── services/        # Servicios de aplicación
├── 📁 infrastructure/   # Implementaciones técnicas
│   ├── http/           # Cliente HTTP
│   ├── adapters/       # Adaptadores externos
│   └── persistence/    # Persistencia en memoria
├── 📁 presentation/     # Interfaz de usuario
│   └── console/        # Interfaz de consola
└── 📁 shared/          # Utilidades compartidas
    ├── constants/      # Constantes
    └── utils/          # Utilidades
```

## 🎯 Patrones de Diseño Implementados

- **Strategy Pattern**: Validaciones específicas por moneda
- **Template Method**: Comportamiento común de monedas
- **Factory Method**: Creación de objetos moneda
- **Adapter Pattern**: Integración con API externa
- **Service Layer**: Lógica de negocio organizada
- **Repository Pattern**: Abstracción de persistencia
- **Facade Pattern**: Interfaz simplificada
- **Dependency Injection**: Bajo acoplamiento

## 🧪 Estructura del Proyecto

```
conversor-monedas/
├── 📁 src/main/java/    # Código fuente
├── 📁 libs/             # Dependencias JAR
├── 📁 out/              # Archivos compilados
├── 📄 README.md         # Documentación
├── 📄 .gitignore        # Archivos ignorados por Git
└── 📄 LICENSE           # Licencia del proyecto
```

## 🚀 Roadmap

### Próximas Funcionalidades
- [ ] **Interfaz Web** con Spring Boot
- [ ] **Base de datos** para persistencia
- [ ] **Cache de tasas** para optimización
- [ ] **Más monedas** (EUR, GBP, JPY, etc.)
- [ ] **Gráficos** de tendencias históricas
- [ ] **API REST** propia
- [ ] **Tests unitarios** y de integración
- [ ] **Docker** containerización

### Mejoras Técnicas
- [ ] **Logging** con SLF4J
- [ ] **Configuración externa** con properties
- [ ] **Métricas** y monitoreo
- [ ] **Documentación API** con OpenAPI

## 🤝 Contribuir

¡Las contribuciones son bienvenidas! Por favor:

1. **Fork** el proyecto
2. Crear una **rama feature** (`git checkout -b feature/nueva-funcionalidad`)
3. **Commit** tus cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. **Push** a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abrir un **Pull Request**

### Guías de Contribución
- Seguir los patrones de código existentes
- Agregar tests para nuevas funcionalidades
- Actualizar documentación cuando sea necesario
- Respetar la arquitectura del proyecto

## 📝 Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para detalles.

## 👨‍💻 Autor

**Tu Nombre** - [@tu-usuario](https://github.com/tu-usuario)

## 🙏 Agradecimientos

- [ExchangeRate-API](https://exchangerate-api.com/) por proporcionar las tasas de cambio
- [Google Gson](https://github.com/google/gson) por el manejo de JSON
- Comunidad Java por las mejores prácticas y patrones

## 📧 Contacto

- **Email**: tu-email@ejemplo.com
- **LinkedIn**: [Tu Perfil](https://linkedin.com/in/tu-perfil)
- **Portfolio**: [tu-portfolio.com](https://tu-portfolio.com)

## 🐛 Reportar Bugs

¿Encontraste un bug? [Abre un issue](https://github.com/tu-usuario/conversor-monedas/issues) con:

- Descripción del problema
- Pasos para reproducir
- Comportamiento esperado vs actual
- Screenshots (si aplica)
- Información del sistema

---

⭐ **¡Dale una estrella al proyecto si te fue útil!** ⭐
