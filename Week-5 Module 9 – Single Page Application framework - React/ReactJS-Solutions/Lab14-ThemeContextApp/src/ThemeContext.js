import { createContext } from 'react';

// Lab 14: Context to share the theme name with nested child components
const ThemeContext = createContext('light');

export default ThemeContext;
