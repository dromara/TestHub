import React, { useState } from 'react';

const MyComponent = () => {
  const [selectedKey, setSelectedKey] = useState('key1');

  const handleChange = (event) => {
    setSelectedKey(event.target.value);
  };

  const Component1 = () => <div>Component 1</div>;
  const Component2 = () => <div>Component 2</div>;
  const Component3 = () => <div>Component 3</div>;

  const componentMap = {
    key1: Component1,
    key2: Component2,
    key3: Component3,
  };

  const SelectedComponent = componentMap[selectedKey];

  return (
    <div>
      <select value={selectedKey} onChange={handleChange}>
        <option value="key1">Component 1</option>
        <option value="key2">Component 2</option>
        <option value="key3">Component 3</option>
      </select>
      <SelectedComponent />
    </div>
  );
};

export default MyComponent;
