import React, { useRef } from 'react';
import { ImperativePanelGroupHandle, Panel, PanelGroup, PanelResizeHandle } from 'react-resizable-panels';

function MyComponent() {
  const ref = useRef<ImperativePanelGroupHandle>(null);

  const resetLayout = () => {
    const panelGroup = ref.current;
    if (panelGroup) {
      // Reset each Panel to 50% of the group's width
      panelGroup.setLayout([50, 50]);
    }
  };

  return (
    <>
      <PanelGroup direction="horizontal" ref={ref}>
        <Panel>left</Panel>
        <PanelResizeHandle />
        <Panel>right</Panel>
      </PanelGroup>
    </>
  );
}

export default MyComponent;
