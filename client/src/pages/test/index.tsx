import React from 'react';

const MyComponent = () => {
    const handleEncrypt = async () => {
        const text = 'Hello, world!';
        const encoder = new TextEncoder();
        const data = encoder.encode(text);

        try {
            const key = await window.crypto.subtle.generateKey(
                { name: 'AES-CBC', length: 256 },
                true,
                ['encrypt', 'decrypt']
            );

            const iv = window.crypto.getRandomValues(new Uint8Array(16));
            const ciphertext = await window.crypto.subtle.encrypt(
                { name: 'AES-CBC', iv: iv },
                key,
                data
            );

            console.log('Encrypted:', new Uint8Array(ciphertext));
        } catch (error) {
            console.error('Encryption error:', error);
        }
    };

    return (
        <div>
            <button onClick={handleEncrypt}>Encrypt</button>
        </div>
    );
};

export default MyComponent;
