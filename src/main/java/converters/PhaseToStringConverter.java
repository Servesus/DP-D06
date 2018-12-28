
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Phase;

@Component
@Transactional
public class PhaseToStringConverter implements Converter<Phase, String> {

	@Override
	public String convert(final Phase p) {
		String result;

		if (p == null)
			result = null;
		else
			result = String.valueOf(p.getId());

		return result;

	}

}
