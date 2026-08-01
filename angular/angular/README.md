# Student Course Portal — Angular (v20) Hands-On Solutions

This project implements all 10 hands-on exercises from the Digital Nurture 5.0
Angular exercise book, built as **one incremental Angular application** (standalone
components, no NgModules) rather than 10 separate projects, per the instructions.

## Getting started

```bash
cd student-course-portal
npm install
npm install -g json-server   # if not already installed

# terminal 1 — mock backend
json-server --watch db.json --port 3000

# terminal 2 — app
npm start   # ng serve, http://localhost:4200
```

Run unit tests: `npm test` (or `npm run test:coverage` for a coverage report).

## Where each hands-on lives

| Hands-On | Topic | Key files |
|---|---|---|
| 1 | Setup, structure, first component | `angular.json`, `tsconfig*.json`, `src/main.ts`, `notes.txt`, `components/header/*`, `pages/home/*` |
| 2 | Binding, lifecycle, @Input/@Output | `pages/home/home.component.ts` (bindings, ngOnInit/ngOnDestroy), `components/course-card/course-card.component.ts` (ngOnChanges, @Input/@Output) |
| 3 | Directives & pipes | `directives/highlight.directive.ts`, `pipes/credit-label.pipe.ts`, `pages/course-list/*` (*ngIf/*ngFor/trackBy), `components/course-card/*` (ngSwitch, ngClass, ngStyle) |
| 4 | Template-driven forms | `pages/enrollment-form/*` |
| 5 | Reactive forms | `pages/reactive-enrollment-form/*` (FormBuilder, custom validator, async validator, FormArray) |
| 6 | Services & DI | `services/course.service.ts`, `services/enrollment.service.ts`, `services/notification.service.ts` (component-scoped provider), `components/course-summary-widget/*` |
| 7 | Routing, guards, lazy loading | `app.routes.ts`, `pages/courses-layout/*`, `pages/course-detail/*`, `guards/auth.guard.ts`, `guards/unsaved-changes.guard.ts`, `features/enrollment/enrollment.routes.ts` (lazy loaded) |
| 8 | HTTP, RxJS, interceptors | `services/course.service.ts` (map/tap/retry/catchError), `interceptors/auth.interceptor.ts`, `interceptors/error-handler.interceptor.ts`, `interceptors/loading.interceptor.ts`, `services/loading.service.ts` |
| 9 | NgRx state management | `store/course/*`, `store/enrollment/*`, wired up in `app.config.ts` and consumed in `pages/course-list/course-list.component.ts` |
| 10 | Unit testing | every `*.spec.ts` file — component tests (`course-card`), service test with `HttpClientTestingModule` (`course.service.spec.ts`), and an NgRx `MockStore` test (`course-list.component.spec.ts`) |

## Notes

- Built with **standalone components** (Angular 17+/20 default) — `app.config.ts`
  replaces `app.module.ts` and registers the router, HttpClient (with interceptors),
  and the NgRx store/effects/devtools.
- `db.json` is the mock REST API consumed by `json-server` for Hands-On 8 onward.
- This was hand-authored to match the exercise book exactly; if you'd rather scaffold
  it yourself with the Angular CLI (`ng generate component/service/...`), the file/folder
  names here match what the CLI would produce, so you can diff against it.
