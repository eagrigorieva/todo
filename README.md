# todoList

## Сборка и запуск

Сборка проекта:

```
mvn clean package
```

Запуск:

```
java -jar .\target\todo-jar-with-dependencies.jar
```

## Описание команд

### create

```
create <описание задачи>
```

Описание задачи может содержать любые символы, кроме перевода строки. Перевод строки (нажатие клавиши Enter) означает
завершение ввода описания задачи

### print

```
print [all]
```

Выводит на печать список задач. По-умолчанию выводятся только невыполненные задачи, в случае если команда выполняется с
аргументом all - печатаются все задачи. Печатаются следующие поля: идентификатор (номер, в данном случае всегда "1"),
который используется в команде toggle, статус задачи (x - выполнена, " " - не выполнена), описание задачи.

Пример вывода:

```
1. [x] Реализовать сборку на maven
```

### toggle

```
toggle <идентификатор задачи>
```

Переключает состояние задачи (с "выполнена" на "не выполнена" и наоборот) по идентификатору.

### search

```
search <substring>
```

Выводит на печать список задач, описание которых содержит substring. Вывод на печать в формате, аналогичном команде
print.

Пример вывода:

```
1. [x] Реализовать сборку на maven
2. [ ] Реализовать сборку на gradle
```

### delete

```
delete <идентификатор задачи>
```

Удаляет задачу из списка задач.

### edit

```
edit <идентификатор задачи> <новое значение>
```

Меняет описание задачи.

### quit

```
quit
```

Завершает работу программы
