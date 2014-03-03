# A Test Project

This is a simple test project. This section is just for documentation and is not evaluated by the markdown modeller.

## Domain Model

### Master Data

#### Music Style

- id : Long
- name : String

#### Country

- id : Long
- code : String

### Business Objects

#### Band

- id : Long
- name : String
- styles : List[Music Style]
- countries : List[Country]

## Source Code
### Java
  