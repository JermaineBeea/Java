package HashBased;

import java.util.HashMap;
import java.util.Map;

public class Maps {

    // Using Java 9+ Map.of() for small immutable maps
Map<String, String> map1 = Map.of(
    "key1", "value1",
    "key2", "value2",
    "key3", "value3"
);

// Using Map.ofEntries() for more entries
Map<String, String> map2 = Map.ofEntries(
    Map.entry("key1", "value1"),
    Map.entry("key2", "value2"),
    Map.entry("key3", "value3")
);

// Using double brace initialization (not recommended but compact)
Map<String, String> map3 = new HashMap<>() {{
    put("key1", "value1");
    put("key2", "value2");
    put("key3", "value3");
}};
    
}
