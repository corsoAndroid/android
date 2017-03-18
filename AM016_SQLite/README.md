# AM016_SQLite

Il seguente esercizio, ampiamente commentato nel codice, presenta le seguenti classi.

## MySQLHelper

E' il motore interno con cui Android gestisce il db SQLite, si esamini con dettaglio le API: [qui](https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html).	Qui abbiamo fatto l'override dei metodi
```
onCreate()
onUpgrade()
```
oltre a riscrivere il costruttore.

## Product

E' il **model** detto alla **MVC** (pattern); con metodi get e set e costruttore.

## ProductsDataSource

E' un'altra classe di aiuto (poteva essere creata con il pattern **singleton**). Ecco i campi
```
private SQLiteDatabase database;
private MySQLiteHelper dbHelper;
private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME,  MySQLiteHelper.COLUMN_DESCRIPTION};
```
il primo ed terzo di questi hanno un valore puramente pratico. Con i metodi
```
public void open()
public void close()
```
apriamo in modalità di **lettura e scrittura** il db e lo chiudiamo utilizzando l'helper. Abbiamo quindi i metodi **CRUD** (hanno un valore puramente esemplificativo)
```
void createProduct(Product p)
void deleteProduct(Product p)
void deleteProduct(String name)
public List<Product> getAllProducts()
public void deleteAll()
```
per implementare i quali abbiamo fatto riferimento alle API di SQLiteDatabase: [qui](https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html). Il codice è ampiamente commentato e si invita, come al solito, a leggere con attenzione le API.
Il metodo
```
public void initialize()
```
serve a popolare il db con un elenco di prodotti.








