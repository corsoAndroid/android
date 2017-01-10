# AM001_Intent

In questa prima esercitazione lavorando con **Button** e **Activity**. Procediamo con due brevi esercizi.

**1)** Creare un bottone sul layout dell'activity principale in modo tale che premendolo ne salti fuori un **Toast**.

Diamo il seguente segmento di codice per aiutare a risolvere la cosa.
```
Button btn = (Button) findViewById(R.id.button);
btn.setOnClickListener(new View.OnClickListener() {
    public void onClick(View view) {
        Toast.makeText(getBaseContext(), "Button was clicked!",
          Toast.LENGTH_SHORT).show();
        }
}); 
```
Consigliamo sempre di andare a vedere le API degli oggetti che utilizziamo.

**2)** Mediante un **Intent**, sempre con un bottone lanciare una seconda **Activity**.

Diamo del codice per raggiungere l'obiettivo. In generale procediamo così.
```
Intent i = new Intent();
i.setAction("com.example.genji.Activity2");
startActivity(i);
```
Nel nostro caso invece.
```
Intent i = new Intent(MainActivity.this, ActivityTwo.class);
startActivity(i);
```
Andare a consultare l'API: [qui](https://developer.android.com/reference/android/content/Intent.html).