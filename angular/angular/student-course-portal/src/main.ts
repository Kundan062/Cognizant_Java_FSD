import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

// main.ts is the application entry point — it bootstraps the root
// standalone AppComponent directly (no AppModule in standalone mode).
bootstrapApplication(AppComponent, appConfig).catch((err) => console.error(err));
