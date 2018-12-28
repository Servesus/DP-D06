
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Note;

@Component
@Transactional
public class NoteToStringConverter implements Converter<Note, String> {

	@Override
	public String convert(final Note n) {
		String result;

		if (n == null)
			result = null;
		else
			result = String.valueOf(n.getId());

		return result;

	}
}
