/** @type {import('tailwindcss').Config} */
module.exports = {
    mode: process.env.NODE_ENV ? 'jit' : undefined,
    content: ['./src/main/resources/**/*.{html,jsx,tsx}', './src/main/frontend/**/*.{html,jsx,tsx}'],
    darkMode: true, // or 'media' or 'class'
    theme: {
        extend: {},
    },
    variants: {
        extend: {},
    },
    plugins: [],
}

