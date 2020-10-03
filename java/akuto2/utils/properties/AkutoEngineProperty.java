package akuto2.utils.properties;

import akuto2.utils.enums.EnumEngineType;
import akuto2.utils.enums.EnumWorkType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;

public class AkutoEngineProperty {
	public static final IProperty<EnumFacing> BLOCK_FACING = PropertyEnum.create("facing", EnumFacing.class, EnumFacing.Plane.HORIZONTAL.facings());
	public static final IProperty<EnumEngineType> ENGINE_TYPE = PropertyEnum.create("type", EnumEngineType.class);
	public static final IProperty<EnumWorkType> WORK_TYPE = PropertyEnum.create("work", EnumWorkType.class);
}
