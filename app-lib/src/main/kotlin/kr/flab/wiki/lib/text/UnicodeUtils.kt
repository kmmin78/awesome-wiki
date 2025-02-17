package kr.flab.wiki.lib.text

/*
 * Referenced from: https://www.unicode.org/Public/UCD/latest/ucd/PropList.txt
 * Pretty display at: https://www.fileformat.info/info/unicode/category/Zs/list.htm
 *
 * Type notation to ensure this collection is immutable even though a reference leakage happens
 */
@Suppress("DuplicatedCode")
private val UNICODE_BLANK_CHARS: Set<Char> = setOf(
    '\u0009', // control-0009
    '\u000A', // control-000A
    '\u000B', // control-000B
    '\u000C', // control-000C
    '\u000D', // control-000D
    '\u001C', // FILE SEPARATOR
    '\u001D', // GROUP SEPARATOR
    '\u001E', // RECORD SEPARATOR
    '\u001F', // UNIT SEPARATOR
    '\u0020', // SPACE
    '\u0085', // control-0085
    '\u00A0', // NO-BREAK SPACE
    '\u1680', // OGHAM SPACE MARK
    '\u2000', // EN QUAD
    '\u2001', // EM QUAD
    '\u2002', // EN SPACE
    '\u2003', // EM SPACE
    '\u2004', // THREE-PER-EM SPACE
    '\u2005', // FOUR-PER-EM SPACE
    '\u2006', // SIX-PER-EM SPACE
    '\u2007', // FIGURE SPACE
    '\u2008', // PUNCTUATION SPACE
    '\u2009', // THIN SPACE
    '\u200A', // HAIR SPACE
    '\u202F', // NARROW NO-BREAK SPACE
    '\u205F', // MEDIUM MATHEMATICAL SPACE
    '\u3000'  // IDEOGRAPHIC SPACE
)

@SuppressWarnings("ReturnCount")
fun String?.isNullOrUnicodeBlank(): Boolean {
    if (isNullOrBlank()) {
        return true
    }

    this.forEach {
        if (!UNICODE_BLANK_CHARS.contains(it)) {
            return false
        }
    }

    return true
}
