import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;

public class IgnoreNestedAndBuilderClassesHelper implements ImportOption {
    @Override
    public boolean includes(Location location) {
        String path = location.toString();
        return !path.contains("$") && !path.endsWith("Builder.class");
    }
}
