# AM_008

La vecchia versione è stata sistemata in modo da suggerire in modo più coerente le buone pratiche, sempre seguendo i dogmi del material Design. Spunti sono stati trovati in rete ed ora abbiamo esempi che servono praticamente a tutto. Molto probabilmente in un altro esempio proporremo qualcosa di più complesso.

- Per Data e Ora rimandiamo al classico tutorial di Android Developers: [qui](https://developer.android.com/guide/topics/ui/controls/pickers.html) ove si fa uso dei `Fragment` (ecco il motivo per cui questo esercizio dovrebbe essere inserito dopo lo studio di questi).
- Gli `Alert` sono stati costruiti tutti senza `Fragment`. Nel caso 3 abbiamo usato la strategia di riconfigurare il `Context` a partire dal preesistente. 
`
new ContextThemeWrapper(this, R.style.MyAlertDialogStyle)
`
Qui di seguito il dettaglio dell'`Alert` più customizzato
```
builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
```
Al `Builder` (vedi API (qui) applichiamo uno stile. Aggiungiamo un widget - `View` - al tutto rispettando lo stile dell'alert.
```
final EditText etNickName = new EditText(new ContextThemeWrapper(this, R.style.MyAlertDialogStyle));
builder.setView(etNickName);
```
e quindi i bottoni
```
builder.setPositiveButton("Enter user name", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
```
Per terminare mostriamo l'`Alert`
```
builder.show();
```
Suggeriamo come al solito di andare a leggerse le altre API.


